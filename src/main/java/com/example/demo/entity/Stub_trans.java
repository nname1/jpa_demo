package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@SqlResultSetMapping(
        name = "EventTransDTO",
        classes = {
                @ConstructorResult(
                        targetClass = EventTransDTO.class,
                        columns = {
                                @ColumnResult(name = "eventId", type = Long.class),
                                @ColumnResult(name = "transId", type = Long.class)
                        }
                )
        }
)
@NamedNativeQuery(
        name = "Stub_trans.findAllTransByEventId",
        query = "select st.ID as transId,t.event_id as eventId from stub_trans st, tickets t where st.TICKET_ID = t.id and t.event_id= :eventId",
        resultSetMapping = "EventTransDTO"
)
@Data
@Entity
public class Stub_trans {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "BUYER_ID")
    private Long buyerId;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "TOTAL_COST")
    private float totalCost;

    @Column(name = "SELLER_ID")
    private Long sellerId;

    @ManyToOne
    @JoinColumn(name = "ticket_id",insertable = false, updatable = false)
    private Ticket ticket;
}
