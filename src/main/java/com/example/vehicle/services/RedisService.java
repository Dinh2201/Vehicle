package com.example.vehicle.services;

import com.fasterxml.jackson.core.type.TypeReference;

public interface RedisService {
    <T> void setValue(final String key, T data);

    <T> void setValue(final String key, T data, int expireDuration);

//    <T> T getValue(final String key, Class<T> valueType);
    <T> T getValue(String key, TypeReference<T> typeReference);

    void deleteByKey(final String key);
}
