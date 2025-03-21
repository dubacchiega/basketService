package com.bacchiega.basketservice.client;

import com.bacchiega.basketservice.client.response.PlatziProductResponse;
import com.bacchiega.basketservice.exceptions.CustomErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PlatziStoreClient", url = "${basket.client.platzi}", configuration = CustomErrorDecoder.class)
public interface PlatziStoreClient {

    @GetMapping("/products")
    List<PlatziProductResponse> getAllProducts();

    @GetMapping("/products/{id}")
    PlatziProductResponse getProductsById(@PathVariable Long id);
}
