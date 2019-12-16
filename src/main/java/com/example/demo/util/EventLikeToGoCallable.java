package com.example.demo.util;

import com.example.demo.bo.LikeToGoBO;
import com.example.demo.dto.EventLikeToGoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.concurrent.Callable;

public class EventLikeToGoCallable implements Callable<EventLikeToGoDTO> {

    private String eventId;
    private LikeToGoBO likeToGoBO;

    public EventLikeToGoCallable(String eventId,LikeToGoBO likeToGoBO){
        this.eventId = eventId;
        this.likeToGoBO = likeToGoBO;
    }
    @Override
    public EventLikeToGoDTO call(){
        if(this.eventId == null){
            throw new IllegalArgumentException("Event Id is NULL");
        }
        EventLikeToGoDTO eventLikeToGoDTO = likeToGoBO.getLikeToGoByEventId(this.eventId);

        return eventLikeToGoDTO;
    }
}
