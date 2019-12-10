package com.example.demo.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Event {

    @Id
    @Column(name = "EVENT_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "PLACE_ID")
    private Long placeId;

    @Column(name = "BOB_ID")
    private Long bobId;

    @Column(name = "NAME")
    private String name;

    protected Event(){

    }

}
