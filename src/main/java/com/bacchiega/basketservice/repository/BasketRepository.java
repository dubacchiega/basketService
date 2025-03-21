package com.bacchiega.basketservice.repository;

import com.bacchiega.basketservice.entity.Basket;
import com.bacchiega.basketservice.entity.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BasketRepository extends MongoRepository<Basket, String> {

    Optional<Basket> findByClientAndStatus(Long client, Status status);
}
