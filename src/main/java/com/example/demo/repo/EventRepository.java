package com.example.demo.repo;

import com.example.demo.entity.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends CrudRepository<Event,Long> {

    Optional<Event> findById(Long id);

}
