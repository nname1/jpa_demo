package com.example.demo.util;

import com.example.demo.bo.LikeToGoBO;
import com.example.demo.dto.EventLikeToGoDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Callable;

public class EventLikeToGoCallable implements Callable<EventLikeToGoDTO> {

    private String eventId;

    @Autowired
    private LikeToGoBO likeToGoBO;

    public EventLikeToGoCallable(String eventId){
        this.eventId = eventId;
    }
    @Override
    public EventLikeToGoDTO call() throws Exception {
        EventLikeToGoDTO eventLikeToGoDTO = new EventLikeToGoDTO();
        if(this.eventId == null){
            throw new IllegalArgumentException("Event Id is NULL");
        }
        eventLikeToGoDTO = likeToGoBO.getLikeToGoByEventId(this.eventId);

        return eventLikeToGoDTO;
    }
}
