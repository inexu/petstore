package io.swagger;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.azure.cosmos.ConsistencyLevel;
import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.CosmosDatabase;
import com.azure.cosmos.models.CosmosContainerProperties;
import com.azure.cosmos.models.CosmosContainerResponse;
import com.azure.cosmos.models.CosmosDatabaseResponse;
import com.chtrembl.petstore.order.model.ContainerEnvironment;
import com.microsoft.applicationinsights.attach.ApplicationInsights;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication(exclude={ DataSourceAutoConfiguration.class})
@SpringBootApplication
@EnableCaching
@EnableSwagger2
@ComponentScan(basePackages = { "io.swagger", "io.swagger.repo", "com.chtrembl.petstore.order.api", "io.swagger.configuration" })
public class Swagger2SpringBoot implements CommandLineRunner {
	static final Logger log = LoggerFactory.getLogger(Swagger2SpringBoot.class);

	@Value("${spring.cloud.azure.cosmos.enabled}")
	private boolean externalDatabaseEnabled;

	@Bean
	public CosmosClient cosmosClient(
		  @Value("${spring.cloud.azure.cosmos.endpoint}") String accountHost,
		  @Value("${spring.cloud.azure.cosmos.key}") String accountKey) {
		return externalDatabaseEnabled ? new CosmosClientBuilder()
			  .endpoint(accountHost)
			  .key(accountKey)
			  .consistencyLevel(ConsistencyLevel.EVENTUAL)
			  .buildClient() : null;
	}

	@Bean
	public CosmosDatabase cosmosDatabase(
		  Optional<CosmosClient> cosmosClient, @Value("${spring.cloud.azure.cosmos.database}") String databaseName) {
		if (!externalDatabaseEnabled)
		{
			return null;
		}

		CosmosDatabaseResponse databaseResponse = cosmosClient.get().createDatabaseIfNotExists(databaseName);
		return cosmosClient.get().getDatabase(databaseResponse.getProperties().getId());
	}

	@Bean
	public CosmosContainer cosmosContainer(
		  Optional<CosmosDatabase> cosmosDatabase, @Value("${spring.cloud.azure.cosmos.container}") String containerName) {
		if (!externalDatabaseEnabled)
		{
			return null;
		}

		CosmosContainerProperties containerProperties =
			  new CosmosContainerProperties(containerName, "/id");
		CosmosContainerResponse containerResponse = cosmosDatabase.get().createContainerIfNotExists(containerProperties);
		return cosmosDatabase.get().getContainer(containerResponse.getProperties().getId());
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public ContainerEnvironment containerEnvvironment() {
		return new ContainerEnvironment();
	}

	@Override
	public void run(String... arg0) throws Exception {
		if (arg0.length > 0 && arg0[0].equals("exitcode")) {
			throw new ExitException();
		}
	}

	public static void main(String[] args) throws Exception {
		// Disable certificates check for local testing with CosmosDB emulator
//		System.setProperty("javax.net.ssl.trustStore", "NUL");
//		System.setProperty("javax.net.ssl.trustStoreType", "Windows-ROOT");

		ApplicationInsights.attach();
		new SpringApplication(Swagger2SpringBoot.class).run(args);
	}

	class ExitException extends RuntimeException implements ExitCodeGenerator {
		private static final long serialVersionUID = 1L;

		@Override
		public int getExitCode() {
			return 10;
		}
	}
}
