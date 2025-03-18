package com.api.product.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue
    private Integer id;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Product> getProduct() {
        return product;
    }

    public Integer getId() {
        return id;
    }

    private String name;
    private String description;
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Product> product;


    private Category(CategoryBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.product = builder.product;
    }

    // Static inner Builder class
    public static class CategoryBuilder {
        private Integer id;
        private String name;
        private String description;
        private List<Product> product;

        public CategoryBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public CategoryBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CategoryBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CategoryBuilder products(List<Product> products) {
            this.product = products;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }

    // Static method to start the builder
    public static CategoryBuilder builder() {
        return new CategoryBuilder();
    }

}
