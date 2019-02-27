package com.github.acolina.animelist.service;

import com.github.acolina.animelist.model.mal.MalItem;

import java.util.List;

public interface MyAnimeListService {

    List<MalItem> findByWord(String word);
}
