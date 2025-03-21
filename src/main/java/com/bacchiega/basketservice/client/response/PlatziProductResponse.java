package com.bacchiega.basketservice.client.response;

import java.io.Serializable;
import java.math.BigDecimal;

// Serializable é usado para poder manter algo em cache
public record PlatziProductResponse(Long id, String title, BigDecimal price) implements Serializable {
}
