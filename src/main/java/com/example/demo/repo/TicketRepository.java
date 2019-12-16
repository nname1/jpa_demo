package com.example.demo.repo;

import com.example.demo.entity.Ticket;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends CrudRepository<Ticket,Long>, JpaSpecificationExecutor {

    Optional<List<Ticket>> findByEventId(Long eventId);
}
