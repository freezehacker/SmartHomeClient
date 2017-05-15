package dao;

import java.util.List;

/**
 * Created by JK on 2017/4/27.
 */
public interface BaseDao<T> {

    void insert(T t);

    void update(T t);

    void delete(int id);

    T get(int id);

    List<T> list();
}
