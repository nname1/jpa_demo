package com.example.demo.repo;

import com.example.demo.entity.Event;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EventRepository extends CrudRepository<Event,Long>, JpaSpecificationExecutor {

    Optional<Event> findById(Long id);

}
