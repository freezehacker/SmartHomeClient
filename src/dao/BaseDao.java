package dao;

import java.util.List;

/**
 * Created by JK.
 */
public interface BaseDao<T> {

    void insert(T t);

    void update(T t);

    void delete(int id);

    T get(int id);

    List<T> list();
}
