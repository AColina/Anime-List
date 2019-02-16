package com.github.acolina.animelist.service;

import com.github.acolina.animelist.core.exception.EntityValidatedException;
import com.github.acolina.animelist.model.entity.Anime;

import java.util.List;
import java.util.Optional;

public interface AnimeService {

    Anime findByName(String name);

    List<Anime> findByNameLike(String name);

    List<Anime> findAll();

    Anime save(Anime s) throws EntityValidatedException;

    Optional<Anime> findById(Long aLong);

    boolean existsById(Long aLong);

    long count();

    void deleteById(Long aLong);
}
