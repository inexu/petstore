package io.swagger.converter;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.chtrembl.petstore.order.model.Order;

import io.swagger.model.OrderDocument;


@Component
public class Order2OrderDocumentConverter extends Converter<Order, OrderDocument>
{
	@Override
	public void populate(final Order source, final OrderDocument target)
	{
		target.setId(source.getId());
		target.setEmail(source.getEmail());
		target.setProducts(source.getProducts().stream()
			  .filter(Objects::nonNull)
			  .map(product -> product.getId().toString())
			  .toList());
	}

	@Override
	protected OrderDocument createTarget()
	{
		return new OrderDocument();
	}
}
