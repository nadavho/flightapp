package com.flightapp.common.repository;

import java.util.Optional;

public interface DataRepository<E,Id> {

    void save(E entity);

    public Optional<E> findById(Id id);
}
