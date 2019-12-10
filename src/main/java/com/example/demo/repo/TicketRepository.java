package com.example.demo.repo;

import com.example.demo.entity.Stub_trans;
import com.example.demo.entity.Ticket;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends CrudRepository<Ticket,Long>, JpaSpecificationExecutor {

    Optional<List<Ticket>> findByEventId(Long eventId);
}
