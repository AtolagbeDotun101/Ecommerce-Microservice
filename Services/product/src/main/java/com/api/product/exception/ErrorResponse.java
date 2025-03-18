package com.api.product.exception;

import java.util.Map;

public record ErrorResponse (
        Map<String,String> errors
){
}
