package com.example.demo.bo;

import com.example.demo.dto.EventLikeToGoDTO;
import com.example.demo.entity.CatalogType;
import com.example.demo.exception.LikeToGoException;

import javax.management.RuntimeErrorException;
import java.util.List;

public interface LikeToGoBO {

    EventLikeToGoDTO getLikeToGoByEventId(String eventId) throws LikeToGoException;

    List<String> getEventListByCatalogId(String catalogId, CatalogType catalogType) throws LikeToGoException;

    boolean updateLikeToGoByEventId(String eventId) throws LikeToGoException;

}
