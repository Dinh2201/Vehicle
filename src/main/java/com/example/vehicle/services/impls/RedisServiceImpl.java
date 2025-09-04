package com.example.vehicle.services.impls;

import com.fasterxml.jackson.core.type.TypeReference;
import com.example.vehicle.services.RedisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisServiceImpl implements RedisService {

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    private final String ERROR_CONVERTING_MSG = "Error Converting object to json";
    private final String ERROR_PARSING_MSG = "Error Parsing json to object";

    @Override
    public <T> void setValue(String key, T data) {
        try {
            String dataJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
            redisTemplate.opsForValue().set(key, dataJson);
        } catch (JsonProcessingException e) {
            log.error(ERROR_CONVERTING_MSG, e);
        }
    }

    @Override
    public <T> void setValue(String key, T data, int expireDuration) {
        try {
            String dataJson = objectMapper.writeValueAsString(data);
            redisTemplate.opsForValue().set(key, dataJson, expireDuration, TimeUnit.SECONDS);
        }catch (JsonProcessingException e) {
            log.error(ERROR_CONVERTING_MSG, e);
        }
    }

    @Override
    public <T> T getValue(String key, TypeReference<T> typeReference) {
        T t = null;
        try {
            String dataJson = redisTemplate.opsForValue().get(key);
            if (StringUtils.isNotEmpty(dataJson)) {
                t = objectMapper.readValue(dataJson, typeReference);
            }
        } catch (JsonProcessingException e) {
            log.error("Error while parsing JSON from Redis", e);
        }
        return t;
    }

    @Override
    public void deleteByKey(@NotNull String key) {
        redisTemplate.delete(key);
    }
}
