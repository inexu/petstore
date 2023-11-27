package io.swagger.converter;

import org.springframework.stereotype.Component;

import com.chtrembl.petstore.product.model.Product;

import io.swagger.model.ProductModel;


@Component
public class ProductModel2ProductConverter extends Converter<ProductModel, Product>
{
	@Override
	public void populate(ProductModel source, Product target)
	{
		target.setId(source.getId());
		target.setName(source.getName());
		target.setPhotoURL(source.getPhotoUrl());

		if (source.getStatus() != null)
		{
			target.setStatus(Product.StatusEnum.valueOf(source.getStatus().toUpperCase()));
		}

		//todo
	}

	@Override
	protected Product createTarget()
	{
		return new Product();
	}
}
