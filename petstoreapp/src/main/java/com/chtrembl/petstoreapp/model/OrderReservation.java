package com.chtrembl.petstoreapp.model;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;


@SuppressWarnings("serial")
@Component
public class OrderReservation implements Serializable
{
	@JsonProperty("user")
	private Customer user = null;
	@JsonProperty("order")
	private Order order = null;

	public Customer getUser()
	{
		return user;
	}

	public void setUser(final Customer user)
	{
		this.user = user;
	}

	public Order getOrder()
	{
		return order;
	}

	public void setOrder(final Order order)
	{
		this.order = order;
	}

	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final OrderReservation that = (OrderReservation) o;
		return Objects.equals(user, that.user) && Objects.equals(order, that.order);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(user, order);
	}
}
