package com.epam.amatias;

import org.springframework.cloud.service.BaseServiceInfo;

/**
 * Created by Alexey on 27.11.2016.
 */
public class HashMapServiceInfo extends BaseServiceInfo {
    private String url;
    public static final String URI_SCHEME = "hashmap";


    public HashMapServiceInfo(String id, String url) {
        super(id);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
