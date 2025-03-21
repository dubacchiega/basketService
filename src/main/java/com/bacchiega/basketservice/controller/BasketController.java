package com.bacchiega.basketservice.controller;

import com.bacchiega.basketservice.controller.request.BasketRequest;
import com.bacchiega.basketservice.controller.request.PaymentRequest;
import com.bacchiega.basketservice.entity.Basket;
import com.bacchiega.basketservice.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    @GetMapping("/{id}")
    public ResponseEntity<Basket> findBasketById(@PathVariable String id) {
        return ResponseEntity.ok(basketService.getBasketById(id));
    }

    @PostMapping
    public ResponseEntity<Basket> createBasket(@RequestBody BasketRequest basketRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(basketService.createBasket(basketRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Basket> updateBasket(@PathVariable String id, @RequestBody BasketRequest basketRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(basketService.updateBasket(id, basketRequest));
    }

    @PutMapping("/{id}/payment")
    public ResponseEntity<Basket> payBasket(@PathVariable String id, @RequestBody PaymentRequest paymentRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(basketService.payBasket(id, paymentRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBasket(@PathVariable String id) {
        basketService.deleteBasket(id);
        return ResponseEntity.noContent().build();
    }
}

