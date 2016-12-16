package com.example.best.the.androidproject.data;

import java.util.List;

/**
 * Created by NP550P5C-SO4PL on 2016-12-11.
 */

public interface Dao<T> {
    long save(T type);
    void update(T type);
    void delete(T type);
    T get(long id);
    List<T> getAll();
}
