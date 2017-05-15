package dao.impl;

import dao.BaseDao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by JK.
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

    private Class clazz;

    public BaseDaoImpl() {
        this.clazz = (Class)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void insert(T t) {

    }

    @Override
    public void update(T t) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public T get(int id) {
        return null;
    }

    @Override
    public List<T> list() {
        return null;
    }
}
