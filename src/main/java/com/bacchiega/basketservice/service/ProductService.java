package com.bacchiega.basketservice.service;

import com.bacchiega.basketservice.client.PlatziStoreClient;
import com.bacchiega.basketservice.client.response.PlatziProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final PlatziStoreClient platziStoreClient;

    @Cacheable(value = "products")
    public List<PlatziProductResponse> getAllProducts() {
        log.info("Getting all products from Platzi Store");
        return platziStoreClient.getAllProducts();
    }

//    @Cacheable(value = "product", key = "#productId")
//Indica que os resultados desse metodo devem ser armazenados em cache com a chave baseada no productId
    @Cacheable(value = "product", key = "#productId")
    public PlatziProductResponse getProductsById(Long productId) {
        log.info("Getting product by id {} from Platzi Store", productId);
        return platziStoreClient.getProductsById(productId);
    }
}
