package com.api.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class OrderLine {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private Integer productId;
    private double quantity;

    public OrderLine(OrderLineBuilder builder ) {
        this.id = builder.id;
        this.order = builder.order;
        this.productId = builder.productId;
        this.quantity = builder.quantity;
    }

    public static class OrderLineBuilder {
        private Integer id;
        private Order order;
        private Integer productId;
        private double quantity;

        public OrderLineBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public OrderLineBuilder order(Order order) {
            this.order = order;
            return this;
        }

        public OrderLineBuilder productId(Integer productId) {
            this.productId = productId;
            return this;
        }

        public OrderLineBuilder quantity(double quantity) {
            this.quantity = quantity;
            return this;
        }
        public OrderLine build() {
            return new OrderLine(this);
        }
    }
    public static OrderLineBuilder builder() {
        return new OrderLineBuilder();
    }


}
