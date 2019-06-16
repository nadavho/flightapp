package com.flightapp.common.utils.cache;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public abstract class AbstractCache<K, V> implements Cache<K> {

    private Map<CacheEntry<K>, V> cachedEntries = new ConcurrentHashMap<>();

    @Autowired
    protected CacheEvacuator cacheEvacuator;

    public V runWithSupplier(K k,
                             Function<K, V> responseProvider) {
        V value = cachedEntries.get(CacheEntry.key(k));
        return value != null ? value : cacheAndReturn(k, responseProvider.apply(k));
    }

    private V cacheAndReturn(K k, V value) {
        cachedEntries.put(CacheEntry.of(k, System.currentTimeMillis()), value);
        return value;
    }


    @Override
    public Collection<CacheEntry<K>> entries() {
        return cachedEntries.keySet();
    }
}
