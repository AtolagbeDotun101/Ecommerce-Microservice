package com.api.order.entity;


import com.api.order.Enum.PaymentMethod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;


@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer_oder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String reference;
    private BigDecimal totalAmount;
    @Enumerated(STRING)
    private PaymentMethod paymentMethod;
    private String customerId;
    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(insertable = false )
    private LocalDateTime lastModifiedDate;


    private Order(OrderBuilder builder) {
        this.id = builder.id;
        this.reference = builder.reference;
        this.totalAmount = builder.totalAmount;
        this.paymentMethod = builder.paymentMethod;
        this.customerId = builder.customerId;
        this.orderLines = builder.orderLines;
        this.lastModifiedDate = builder.lastModifiedDate;
    }

    public static class OrderBuilder {
        private  Integer id;
        private  String reference;
        private  BigDecimal totalAmount;
        private  PaymentMethod paymentMethod;
        private  String customerId;
        private  List<OrderLine> orderLines;
        private  LocalDateTime lastModifiedDate;


        public OrderBuilder id(Integer id) {
            this.id = id;
            return this;
        }
        public OrderBuilder reference(String reference) {
            this.reference = reference;
            return this;
        }
        public OrderBuilder totalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }
        public OrderBuilder paymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }
        public OrderBuilder customerId(String customerId) {
            this.customerId = customerId;
            return this;
        }
        public OrderBuilder orderLines(List<OrderLine> orderLines) {
            this.orderLines = orderLines;
            return this;
        }
        public OrderBuilder lastModifiedDate(LocalDateTime lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    public static OrderBuilder builder() {
        return new OrderBuilder();
    }


}
