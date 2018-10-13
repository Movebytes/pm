package com.pm.shared;

import java.util.List;

public abstract class AbstractService<E, K> {
    public abstract List<E> getAll();
    public abstract E getEntityById(K id);
    public abstract E update(E entity);
    public abstract void delete(E entity);
    public abstract void create(E entity);
}
