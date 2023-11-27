package io.swagger.repo;

import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;

import io.swagger.model.OrderDocument;


@Repository
public interface OrderRepo extends ReactiveCosmosRepository<OrderDocument, String>
{
}
