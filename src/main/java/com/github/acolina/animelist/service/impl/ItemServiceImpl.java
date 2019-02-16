package com.github.acolina.animelist.service.impl;

import com.github.acolina.animelist.core.exception.EntityValidatedException;
import com.github.acolina.animelist.model.entity.Item;
import com.github.acolina.animelist.repository.ItemRepository;
import com.github.acolina.animelist.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;
    private final Validator validator;

    @Autowired
    public ItemServiceImpl(ItemRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Item findByName(String name) {
        return repository.findByName(name);
    }

    public List<Item> findByNameLike(String name) {
        return repository.findByNameLike(name);
    }

    public List<Item> findAll() {
        return repository.findAll();
    }

    public Item save(Item s) throws EntityValidatedException {
        Set<ConstraintViolation<Object>> error = validator.validate(s);
        if (!error.isEmpty()) {
            throw new EntityValidatedException(error);
        }

        return repository.saveAndFlush(s);
    }

    public Optional<Item> findById(Long aLong) {
        return repository.findById(aLong);
    }

    public boolean existsById(Long aLong) {
        return repository.existsById(aLong);
    }

    public long count() {
        return repository.count();
    }

    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }
}
