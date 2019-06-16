package com.flightapp.common.utils;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
public class Pair<K,V> {

    private final K key;
    private final V value;

    public static <K,V> Pair<K,V>  of(K key,V value){
        return new Pair<>(key,value);
    }

    private Pair(K key,V value){
        this.key = key;
        this.value = value;
    }


}
