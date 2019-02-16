package com.github.acolina.animelist.service;

import com.github.acolina.animelist.core.exception.EntityValidatedException;
import com.github.acolina.animelist.model.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Item findByName(String name);

    List<Item> findByNameLike(String name);

    List<Item> findAll();

    Item save(Item s) throws EntityValidatedException;

    Optional<Item> findById(Long aLong);

    boolean existsById(Long aLong);

    long count();

    void deleteById(Long aLong);
}
