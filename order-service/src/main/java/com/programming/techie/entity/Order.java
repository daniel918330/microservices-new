package com.programming.techie.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_order")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    private Integer totalQuantity;
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderLineItems> orderLineItems;
}
