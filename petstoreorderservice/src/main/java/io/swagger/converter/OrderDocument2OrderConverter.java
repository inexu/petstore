package io.swagger.converter;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.chtrembl.petstore.order.model.Order;
import com.chtrembl.petstore.order.model.Product;

import io.swagger.model.OrderDocument;


@Component
public class OrderDocument2OrderConverter extends Converter<OrderDocument, Order>
{
	@Override
	public void populate(final OrderDocument source, final Order target)
	{
		target.setId(source.getId());
		target.setEmail(source.getEmail());
		target.setProducts(source.getProducts().stream()
			  .filter(Objects::nonNull)
			  .map(productId -> {
				  Product product = new Product();
				  product.setId(Long.valueOf(productId));
				  return product;
			  })
			  .toList());
	}

	@Override
	protected Order createTarget()
	{
		return new Order();
	}
}
