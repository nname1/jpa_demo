package com.example.demo.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Data
@Table(name = "TICKETS")
@SecondaryTable(name="STUB_TRANS",pkJoinColumns=@PrimaryKeyJoinColumn(name="TICKET_ID"))
public class Ticket {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "EVENT_ID")
    private Long eventId;

    @Column(name = "SELLER_ID")
    private Long sellerId;

    @Column(name = "QUANTITY_REMAIN")
    private int quantityRemain;

    @Column(name = "CURR_PRICE")
    private float price;

    @Column(name = "SYSTEM_STATUS")
    private String status;

    @Column(table = "STUB_TRANS", name="ID")
    private Long transId;

}
