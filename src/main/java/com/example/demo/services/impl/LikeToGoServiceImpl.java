package com.example.demo.services.impl;

import com.example.demo.bo.LikeToGoBO;
import com.example.demo.dto.EventLikeToGoDTO;
import com.example.demo.dto.LikeToGoDTO;
import com.example.demo.entity.CatalogType;
import com.example.demo.exception.LikeToGoException;
import com.example.demo.services.LikeToGoService;
import com.example.demo.util.EventLikeToGoCallable;
import com.example.demo.util.PoolService;
import com.zaxxer.hikari.util.UtilityElf;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

@Log4j2
@Service
public class LikeToGoServiceImpl implements LikeToGoService {

    @Autowired
    private LikeToGoBO likeToGoBO;

    @Autowired
    private PoolService poolService;

    @Override
    public LikeToGoDTO getLikeToGoByCatalogId(String catalogId, CatalogType catalogType) throws LikeToGoException{
        List<String> eventList = likeToGoBO.getEventListByCatalogId(catalogId,catalogType);
        LikeToGoDTO likeToGoDTO = new LikeToGoDTO();
        likeToGoDTO.setCatalogId(catalogId);
        List<EventLikeToGoDTO> eventLikeToGoList = new ArrayList<>();
        List<Future<EventLikeToGoDTO>> returnFuture = new ArrayList<>();
        ExecutorService pool = poolService.getPool();
        for(String eventId : eventList){
            returnFuture.add(pool.submit(new EventLikeToGoCallable(eventId,likeToGoBO)));
        }
        while(returnFuture.size() > 0){
            List<Future<EventLikeToGoDTO>> completedFuture = new ArrayList<>();
            for(Future<EventLikeToGoDTO> future : returnFuture){
                try{
                    EventLikeToGoDTO eventLikeToGoDTO = future.get();
                    eventLikeToGoList.add(eventLikeToGoDTO);
                    completedFuture.add(future);
                    //returnFuture.remove(future);

                }catch (InterruptedException e) {
                    e.printStackTrace();
                    throw new LikeToGoException("Got Interrupted Exception : "+e.getLocalizedMessage());

                } catch (ExecutionException e) {
                    e.printStackTrace();
                    throw new LikeToGoException("Got Execution Exception : "+e.getLocalizedMessage());
                }
            }
            returnFuture.removeAll(completedFuture);
        }
        likeToGoDTO.setEventLikeToGoList(eventLikeToGoList);
        return likeToGoDTO;
    }

    @Override
    public boolean updateLikeToGoByEventId(String eventId) {
        return false;
    }
}
