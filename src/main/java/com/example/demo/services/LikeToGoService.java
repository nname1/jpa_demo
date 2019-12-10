package com.example.demo.services;

import com.example.demo.dto.LikeToGoDTO;
import com.example.demo.exception.LikeToGoException;
import org.springframework.stereotype.Service;

@Service
public interface LikeToGoService {

    LikeToGoDTO getLikeToGoByPerformerId(String performerId) throws LikeToGoException;

    boolean updateLikeToGoByEventId(String eventId);
}
