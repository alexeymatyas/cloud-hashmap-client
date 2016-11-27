package com.epam.amatias.rest;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

/**
 * Created by Alexey on 27.11.2016.
 */
public interface CloudHashMapClient {
    void put(String key, Object object) throws JsonProcessingException;

    <T> T get(String key, Class<T> clazz) throws IOException;
}
