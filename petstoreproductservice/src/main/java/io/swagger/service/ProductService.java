package io.swagger.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chtrembl.petstore.product.model.Product;

import io.swagger.converter.ProductModel2ProductConverter;


@Service
public class ProductService
{
//	@Resource
//	private ProductRepo productRepo;
	@Resource
	private ProductModel2ProductConverter productModel2ProductConverter;

	public List<Product> getAllProducts()
	{
//		return productModel2ProductConverter.convertAll(productRepo.findAll());
		return null;
	}
}
