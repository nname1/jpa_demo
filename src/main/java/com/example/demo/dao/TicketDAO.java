package com.example.demo.dao;

import com.example.demo.entity.Ticket;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketDAO extends CrudRepository<Ticket,Long>, JpaSpecificationExecutor {

    Optional<List<Ticket>> findByEventId(Long eventId);
}
