package lv.learn.azure;

import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.OutputBinding;
import com.microsoft.azure.functions.annotation.BlobOutput;
import com.microsoft.azure.functions.annotation.FixedDelayRetry;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.ServiceBusQueueTrigger;
import com.microsoft.azure.functions.annotation.StorageAccount;


public class ServiceBusQueueTriggerJava
{
	@FunctionName("OrderItemsReserver")
	@StorageAccount("AzureWebJobsStorage")
	@FixedDelayRetry(maxRetryCount = 3, delayInterval = "00:00:10")
	public void run(
		  @ServiceBusQueueTrigger(name = "message", queueName = "order", connection = "ServiceBusConnectionString") String message,
		  @BlobOutput(name = "outputBlob", path = "carts/cart-{id}.json", dataType = "binary") OutputBinding<byte[]> outputBlob,
		  final ExecutionContext context
	)
	{
		Logger log = context.getLogger();

		log.info("Java Service Bus Queue trigger function processed a message: " + message);

		outputBlob.setValue(message.getBytes(StandardCharsets.UTF_8));
	}
}
