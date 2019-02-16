package com.github.acolina.animelist.service.impl;

import com.github.acolina.animelist.core.exception.EntityValidatedException;
import com.github.acolina.animelist.model.entity.Anime;
import com.github.acolina.animelist.repository.AnimeRepository;
import com.github.acolina.animelist.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AnimeServiceImpl implements AnimeService {

    private final AnimeRepository repository;
    private final Validator validator;

    @Autowired
    public AnimeServiceImpl(AnimeRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Anime findByName(String name) {
        return repository.findByName(name);
    }

    public List<Anime> findByNameLike(String name) {
        return repository.findByNameLike(name);
    }

    public List<Anime> findAll() {
        return repository.findAll();
    }

    public Anime save(Anime s) throws EntityValidatedException {
        Set<ConstraintViolation<Object>> error = validator.validate(s);
        if (!error.isEmpty()) {
            throw new EntityValidatedException(error);
        }

        return repository.saveAndFlush(s);
    }

    public Optional<Anime> findById(Long aLong) {
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
