package io.swagger.repo;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.models.CosmosItemRequestOptions;
import com.azure.cosmos.models.PartitionKey;
import com.chtrembl.petstore.order.model.Order;


@Repository
public class OrderRepo
{
	private final CosmosContainer container;

	public OrderRepo(Optional<CosmosContainer> container)
	{
		this.container = container.orElse(null);
	}

	public Order save(Order order)
	{
		return container.upsertItem(order, new PartitionKey(order.getId()), new CosmosItemRequestOptions()).getItem();
	}

	public Order findById(String id)
	{
		return container.readItem(id, new PartitionKey(id), Order.class).getItem();
	}
}
