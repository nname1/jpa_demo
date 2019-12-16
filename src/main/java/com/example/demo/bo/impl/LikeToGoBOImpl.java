package com.example.demo.bo.impl;

import com.example.demo.bo.LikeToGoBO;
import com.example.demo.dto.EventLikeToGoDTO;
import com.example.demo.entity.CatalogType;
import com.example.demo.exception.LikeToGoException;
import com.example.demo.repo.StubTransRepository;
import com.example.demo.util.TestBean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikeToGoBOImpl implements LikeToGoBO {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TestBean testBean;

    @Autowired
    private StubTransRepository stubTransRepository;

    @Override
    public EventLikeToGoDTO getLikeToGoByEventId(String eventId) throws LikeToGoException {
        return stubTransRepository.findEventLikeToGo(eventId);
    }

    @Override
    public List<String> getEventListByCatalogId(String catalogId, CatalogType catalogType) throws LikeToGoException {
        List<String> eventList = new ArrayList<>();
        String apiUrl=testBean.getApiUrl();
        String url=apiUrl+"?shstore=1&sort=popularity+desc&perfRows=10&status=active&"+catalogType.getName()+"Id="+catalogId;
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization", "Bearer "+testBean.getToken());
        requestHeaders.add("Accept", "application/json");
        requestHeaders.add("Content-Type", "application/json");
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,requestEntity,String.class);
        JsonParser p = new JsonParser();
        JsonElement e = p.parse(response.getBody().toString());
        JsonArray jEvents= e.getAsJsonObject().get("events").getAsJsonArray();
        for(JsonElement je : jEvents){
            eventList.add(je.getAsJsonObject().get("id").getAsString());
        }

        return eventList;
    }

    @Override
    public boolean updateLikeToGoByEventId(String eventId) throws LikeToGoException {
        return false;
    }
}
