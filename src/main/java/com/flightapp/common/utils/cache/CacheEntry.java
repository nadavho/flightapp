package com.flightapp.common.utils.cache;

import java.util.Objects;

public class CacheEntry<E> {

    public final E entry;
    public final long timestamp;


    public static <E> CacheEntry<E> of(E entry, long timestamp) {
        return new CacheEntry<>(entry, timestamp);
    }

    public static <E> CacheEntry<E> key(E entry) {
        return new CacheEntry<>(entry, -1);
    }

    private CacheEntry(E entry, long timestamp) {
        this.entry = entry;
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheEntry<?> that = (CacheEntry<?>) o;
        return Objects.equals(entry, that.entry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entry);
    }
}
