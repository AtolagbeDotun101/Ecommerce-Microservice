package com.api.product.entity;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setAvailableQuantity(double availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public double getAvailableQuantity() {
        return availableQuantity;
    }

    public Category getCategory() {
        return category;
    }


    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private double availableQuantity;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    private Product(ProductBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
        this.availableQuantity = builder.availableQuantity;
        this.category = builder.category;
    }

    // Static inner class for the Builder
    public static class ProductBuilder {
        private Integer id;
        private String name;
        private String description;
        private BigDecimal price;
        private double availableQuantity;
        private Category category;

        public ProductBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public ProductBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ProductBuilder availableQuantity(double availableQuantity) {
            this.availableQuantity = availableQuantity;
            return this;
        }

        public ProductBuilder category(Category category) {
            this.category = category;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

    // Static method to start the builder
    public static ProductBuilder builder() {
        return new ProductBuilder();
    }


}

