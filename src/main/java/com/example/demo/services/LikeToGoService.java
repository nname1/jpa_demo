package com.example.demo.services;

import com.example.demo.dto.LikeToGoDTO;
import com.example.demo.entity.CatalogType;
import com.example.demo.exception.LikeToGoException;
import org.springframework.stereotype.Service;


public interface LikeToGoService {

    LikeToGoDTO getLikeToGoByCatalogId(String catalogId, CatalogType catalogType) throws LikeToGoException;

    boolean updateLikeToGoByEventId(String eventId);
}
