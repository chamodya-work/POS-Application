package com.springbootacademy.pos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="orders")
public class Order {
    @Id
    @Column(name = "order_id",length = 45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;
    @Column(name = "order_date",columnDefinition = "DATETIME")
    private Date date;
    @Column(name="total",nullable = false)
    private Double total;

    @OneToMany(mappedBy="order")
    private Set<OrderDetails> orderDetail;
}
