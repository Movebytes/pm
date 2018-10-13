package com.pm.shared;

public abstract class AbstractMapper<M, E> {
    public abstract M mapToModel(E entity);
    public abstract E mapToEntity(M model);
}
