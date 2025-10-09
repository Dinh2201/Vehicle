package com.example.vehicle.services;

public interface KafkaService<K, V> {
    //    Send.
    //    Params: topic - the topic
    //            value
    void send(final String topic, V value);

    //    Send.
    //    Params: topic - the topic
    //            Key - the key
    //                value - the value
    void send(final String topic, K key, V value);
}
