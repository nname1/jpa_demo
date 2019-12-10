package com.example.demo.bo;

import com.example.demo.dto.EventLikeToGoDTO;
import com.example.demo.exception.LikeToGoException;

import javax.management.RuntimeErrorException;
import java.util.List;

public interface LikeToGoBO {

    EventLikeToGoDTO getLikeToGoByEventId(String performerId) throws LikeToGoException;

    List<String> getEventListByPerformerId(String performerId) throws LikeToGoException;

    boolean updateLikeToGoByEventId(String eventId) throws LikeToGoException;

}
