package com.bacchiega.basketservice.entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id;
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
