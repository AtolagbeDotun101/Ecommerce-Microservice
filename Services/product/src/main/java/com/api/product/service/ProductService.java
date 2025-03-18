package com.api.product.service;

import com.api.product.dto.ProductPurchaseRequest;
import com.api.product.dto.ProductPurchaseResponse;
import com.api.product.dto.ProductRequest;
import com.api.product.dto.ProductResponse;
import com.api.product.exception.ProductPurchaseException;
import com.api.product.mapper.ProductMapper;
import com.api.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }
     final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Integer createProduct(ProductRequest request) {
        var product = productMapper.toProduct(request);
        return productRepository.save(product).getId();
    }

    public ProductResponse findProductById(Integer id) {
        return productRepository.findById(id)
                .map(productMapper::toProductResponse).
                orElseThrow(()-> new EntityNotFoundException("Product with id::" + id + " not found"));
    }

    public List<ProductResponse> findAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    public List<ProductPurchaseResponse> findAllProductPurchases(List<ProductPurchaseRequest> requests) {
        var productIds = requests.stream()
                .map(ProductPurchaseRequest::id)
                .toList();
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or More product does not exist");
        }
        var storeRequests = requests.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::id))
                .toList();

        var purchasedProducts =  new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = storeRequests.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient quantity for  product with id::" + product.getId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }

}
