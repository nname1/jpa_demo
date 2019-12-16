package com.example.demo.dao;

import com.example.demo.dto.EventLikeToGoDTO;
import com.example.demo.dto.EventTransDTO;
import com.example.demo.entity.Stub_trans;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StubTransDAO extends CrudRepository<Stub_trans,Long>{

    Optional<List<Stub_trans>> findByTicketId(Long ticketId);
    @Query(nativeQuery=true)
    List<EventTransDTO> findAllTransByEventId(@Param("eventId") String eventId);

    @Query(nativeQuery=true)
    EventLikeToGoDTO findEventLikeToGo(@Param("eventId") String eventId);
}
