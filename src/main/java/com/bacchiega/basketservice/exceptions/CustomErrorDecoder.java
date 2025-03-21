package com.bacchiega.basketservice.exceptions;

import feign.codec.ErrorDecoder;

//Essa classe é responsável por interceptar e tratar erros que ocorrem durante chamadas HTTP feitas por um cliente Feign.

public class CustomErrorDecoder implements ErrorDecoder {

//    A classe CustomErrorDecoder implementa o metodo decode, que é chamado quando ocorre um erro em uma requisição Feign
    @Override
    public Exception decode(String methodKey, feign.Response response) {
        switch (response.status()) {
            case 400: // se for erro 400, retorna DataNotFoundException
                return new DataNotFoundException("Product not found");
            default:
                return new Exception("Exception while getting product");
        }
    }
}
