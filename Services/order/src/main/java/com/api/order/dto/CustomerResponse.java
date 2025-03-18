package com.api.order.dto;

public record CustomerResponse(
        String id,
        String firstname,
        String lastName,
        String email
) {
}
