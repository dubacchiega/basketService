package com.bacchiega.basketservice.service;

import com.bacchiega.basketservice.client.response.PlatziProductResponse;
import com.bacchiega.basketservice.controller.request.BasketRequest;
import com.bacchiega.basketservice.controller.request.PaymentRequest;
import com.bacchiega.basketservice.entity.Basket;
import com.bacchiega.basketservice.entity.Product;
import com.bacchiega.basketservice.entity.Status;
import com.bacchiega.basketservice.exceptions.BusinessException;
import com.bacchiega.basketservice.exceptions.DataNotFoundException;
import com.bacchiega.basketservice.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;

    public Basket getBasketById(String id) {
        return basketRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Basket not found"));
    }

    public Basket createBasket(BasketRequest basketRequest) {

        // A query busca primeiro o id do cliente e depois verifica se o status é OPEN.
        basketRepository.findByClientAndStatus(basketRequest.clientId(), Status.OPEN)
                .ifPresent(basket -> {
                    throw new BusinessException("There is already an open basket for this client");
                });

        final List<Product> products = getProducts(basketRequest);

        Basket basket = Basket.builder() // crio um novo basket
                .client(basketRequest.clientId()) // passo o id do cliente recebido no basketRequest
                .status(Status.OPEN) // defino que a cesta está aberta
                .products(products) // atribuo meu products
                .build();

        basket.calculateTotalPrice(); // calculo o total
        return basketRepository.save(basket);
    }

    public Basket updateBasket(String basketId, BasketRequest basketRequest) {
        Basket savedBasket = getBasketById(basketId);

        final List<Product> products = getProducts(basketRequest);

        savedBasket.setProducts(products);
        savedBasket.calculateTotalPrice();
        return basketRepository.save(savedBasket);
    }

    public Basket payBasket(String basketId, PaymentRequest paymentRequest) {
        Basket savedBasket = getBasketById(basketId);
        savedBasket.setPaymentMethod(paymentRequest.getPaymentMethod());
        savedBasket.setStatus(Status.SOLD);
        return basketRepository.save(savedBasket);
    }

    public void deleteBasket(String id) {
        basketRepository.delete(getBasketById(id));
    }

    private List<Product> getProducts(BasketRequest basketRequest) {
        List<Product> products = new ArrayList<>();
        // percorro a lista de produtos do basketRequest
        basketRequest.products().forEach(productRequest -> {
            // obtenho o produto a partir do id do produto informado pelo usuário (pego o produto da API externa)
            PlatziProductResponse platziProductResponse = productService.getProductsById(productRequest.id());

            // adiciono o produto na lista de produtos
            products.add(Product.builder()
                    .id(platziProductResponse.id())
                    .title(platziProductResponse.title())
                    .price(platziProductResponse.price())
                    .quantity(productRequest.quantity())
                    .build());
        });
        return products;
    }
}
