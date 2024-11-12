package com.programming.techie.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_inventory")

public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sku_code",nullable = false,unique = true)
    private String skuCode;

    @Column(name = "sku_quantity")
    private Integer skuQuantity;
}
