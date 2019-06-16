package com.flightapp.common.utils.cache;

import java.util.Collection;

public interface Cache<E> {


    Collection<CacheEntry<E>> entries();
}
