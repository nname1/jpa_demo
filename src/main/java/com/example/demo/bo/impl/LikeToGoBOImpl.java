package com.example.demo.bo.impl;

import com.example.demo.bo.LikeToGoBO;
import com.example.demo.dto.EventLikeToGoDTO;
import com.example.demo.exception.LikeToGoException;

import java.util.List;

public class LikeToGoBOImpl implements LikeToGoBO {
    @Override
    public EventLikeToGoDTO getLikeToGoByEventId(String performerId) throws LikeToGoException {
        return null;
    }

    @Override
    public List<String> getEventListByPerformerId(String performerId) throws LikeToGoException {
        return null;
    }

    @Override
    public boolean updateLikeToGoByEventId(String eventId) throws LikeToGoException {
        return false;
    }
}
