package ch.zli.m223.punchclock;

import java.util.List;

public interface ICreateReadUpdateDelete<T> { // CRUD
    public T create(T entity);
    public List<T> list();
    public T get(Long id);
    public T update(T entity);
    public void delete(Long id);
}
