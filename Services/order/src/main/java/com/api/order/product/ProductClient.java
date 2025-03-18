package com.api.order.product;

import com.api.order.config.RestTemplate;
import com.api.order.dto.PurchaseRequest;
import com.api.order.dto.PurchaseResponse;
import com.api.order.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

import static jakarta.ws.rs.HttpMethod.POST;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productUrl;
//    private final RestClient restClient;

    private final RestTemplate restTemplate;

//    public List<PurchaseResponse> getPurchases(PurchaseRequest purchaseRequest) {
//        try {
//            return restClient.post()
//                    .uri(productUrl + "/purchase")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .body(purchaseRequest)
//                    .retrieve()
//                    .onStatus(HttpStatusCode::isError, (req, res) -> {
//                        throw new RuntimeException("Failed to fetch purchases");
//                    })
//                    .body(new ParameterizedTypeReference<>() {});
//        } catch (Exception ex) {
//            throw new RuntimeException("Error calling purchase API: " + ex.getMessage(), ex);
//        }
//    }


    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requestBody, headers);
        ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate.exchange(
                productUrl + "/purchase",
                POST,
                requestEntity,
                responseType
        );

        if (responseEntity.getStatusCode().isError()) {
            throw new BusinessException("An error occurred while processing the products purchase: " + responseEntity.getStatusCode());
        }
        return  responseEntity.getBody();
    }

}
