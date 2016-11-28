package com.epam.amatias.rest.impl;

import com.epam.amatias.HashMapServiceInfo;
import com.epam.amatias.rest.CloudHashMapClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.ServiceInfo;
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
@Profile("cloud")
public class CloudHashMapClientImpl implements CloudHashMapClient {
    private static final Log logger = LogFactory.getLog(CloudHashMapClientImpl.class);

    private String url;
    private HttpHeaders httpHeaders;
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void init() {
        CloudFactory cloudFactory = new CloudFactory();
        Cloud cloud = cloudFactory.getCloud();
        url = null;

        List<ServiceInfo> serviceInfos = cloud.getServiceInfos();
        for(ServiceInfo serviceInfo: serviceInfos) {
            if(serviceInfo instanceof HashMapServiceInfo) {
                HashMapServiceInfo hsi = (HashMapServiceInfo) serviceInfo;
                url = ((HashMapServiceInfo) serviceInfo).getUrl();
            }
        }

        logger.error("No hashmap instance bound to this app!");

        String plainCredentials = "admin:admin";
        byte[] plainCredentialsBytes = plainCredentials.getBytes();
        byte[] base64CredentialsBytes = Base64.getEncoder().encode(plainCredentialsBytes);
        String base64Credentials = new String(base64CredentialsBytes);

        httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.add("Authorization", "Basic " + base64Credentials);
    }

    @Override
    public void put(String key, Object object) throws JsonProcessingException {
        Map<String, String> vars = new HashMap<>();
        vars.put("key", key);

        HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(object), httpHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url + "/put/{key}",
                HttpMethod.POST, entity, String.class, vars);
    }

    @Override
    public <T> T get(String key, Class<T> clazz) throws IOException {
        Map<String, String> vars = new HashMap<>();
        vars.put("key", key);

        HttpEntity<String> entity = new HttpEntity<>("", httpHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url + "/get/{key}",
                HttpMethod.GET, entity, String.class, vars);
        return objectMapper.readValue(responseEntity.getBody(), clazz);
    }
}
