package com.bacchiega.basketservice.controller.request;

import com.bacchiega.basketservice.entity.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private PaymentMethod paymentMethod;
}
