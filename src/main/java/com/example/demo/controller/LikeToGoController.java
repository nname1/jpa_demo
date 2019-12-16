package com.example.demo.controller;

import com.example.demo.dto.LikeToGoDTO;
import com.example.demo.entity.CatalogType;
import com.example.demo.services.LikeToGoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class LikeToGoController {

    @Autowired
    private LikeToGoService likeToGoService;

    @RequestMapping(value = "/liketogo/{catalogType}/{catalogId}", method = RequestMethod.GET)
    @ResponseStatus(OK)
    @ResponseBody
    public LikeToGoDTO getLikeToGoByCatalogId(@PathVariable String catalogType, @PathVariable String catalogId) {
        return likeToGoService.getLikeToGoByCatalogId(catalogId, CatalogType.fromName(catalogType));
    }

}
