package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Stub_trans {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "BUYER_ID")
    private Long buyerId;

    @PrimaryKeyJoinColumn
    @Column(name = "TICKET_ID")
    private Long ticketId;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "TOTAL_COST")
    private float totalCost;

    @Column(name = "SELLER_ID")
    private Long sellerId;

}
