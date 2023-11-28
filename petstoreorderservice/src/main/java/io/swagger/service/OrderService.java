package io.swagger.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.azure.cosmos.implementation.NotFoundException;
import com.chtrembl.petstore.order.model.Order;

import io.swagger.repo.OrderRepo;


@Service
public class OrderService
{
	private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

	private final OrderRepo orderRepo;

	public OrderService(OrderRepo orderRepo)
	{
		this.orderRepo = orderRepo;
	}

	public Order save(Order order)
	{
		try
		{
			LOG.info("Saving order={} to database", order);
			return orderRepo.save(order);
		}
		catch (Exception e)
		{
			LOG.error("Error while saving order={}", order, e);
			return null;
		}
	}

	public Order findById(String id)
	{
		try
		{
			return orderRepo.findById(id);
		}
		catch (NotFoundException e)
		{
			LOG.warn("Order with id {} not found in database. Creating new order...", id);
			return new Order();
		}
	}
}
