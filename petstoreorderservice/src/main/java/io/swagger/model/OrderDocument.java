package io.swagger.model;

import java.util.List;
import java.util.Objects;
import javax.persistence.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;


@Container(containerName = "order")
public class OrderDocument
{
	@Id
	private String id;
	@PartitionKey
	private String email;
	private List<String> products;

	public String getId()
	{
		return id;
	}

	public void setId(final String id)
	{
		this.id = id;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(final String email)
	{
		this.email = email;
	}

	public List<String> getProducts()
	{
		return products;
	}

	public void setProducts(final List<String> products)
	{
		this.products = products;
	}

	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final OrderDocument that = (OrderDocument) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id);
	}
}
