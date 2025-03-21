package com.bacchiega.basketservice.controller.request;


// só passo o id e a quantidade, pois o resto vai vir na hora q eu for construir o produto com basse no id passado
public record ProductRequest(Long id, Integer quantity) {
}
