package com.epam.amatias.rest.impl;

import com.epam.amatias.rest.CloudHashMapClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.cloud.service.UriBasedServiceInfo;
import org.springframework.context.annotation.Profile;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

/**
 * Created by Alexey on 27.11.2016.
 */
@Service
@Profile("local")
public class LocalHashMapClientImpl implements CloudHashMapClient {
    private Map<String, String> map;
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        map = new HashMap<>();
    }

    @Override
    public void put(String key, Object object) throws JsonProcessingException {
        map.put(key, objectMapper.writeValueAsString(object));
    }

    @Override
    public <T> T get(String key, Class<T> clazz) throws IOException {
        return objectMapper.readValue(map.get(key), clazz);
    }
}
