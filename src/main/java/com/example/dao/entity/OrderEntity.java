package com.example.dao.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")
@Entity
public class OrderEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "product_id")
    @ManyToOne
    private ProductEntity product;
    @JoinColumn(name = "card_id")
    @ManyToOne
    private CardEntity card;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "total_amount")
    private Double totalAmount;
    @Column(name = "order_date")
    private LocalDateTime orderDate;



}
