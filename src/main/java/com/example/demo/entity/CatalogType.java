package com.example.demo.entity;

import lombok.extern.log4j.Log4j2;

@Log4j2
public enum CatalogType {
    PERFORMER("performer"),VENUE("venue"),GROUPING("grouping");

    private String name;
    CatalogType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static CatalogType fromName(String fulfillmentWindow) {
        for (CatalogType f : CatalogType.values()) {
            if (f.getName().equalsIgnoreCase(fulfillmentWindow)) {
                return f;
            }
        }
        log.error("invalid fulfillment method value: " + fulfillmentWindow);
        return null;
    }
}
