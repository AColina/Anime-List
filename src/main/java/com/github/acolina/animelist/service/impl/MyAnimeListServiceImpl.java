package com.github.acolina.animelist.service.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.acolina.animelist.model.mal.MalItem;
import com.github.acolina.animelist.service.MyAnimeListService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyAnimeListServiceImpl implements MyAnimeListService {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    @Value("${mal.endpoint.url}")
    private String malUrl;

    @Autowired
    public MyAnimeListServiceImpl(RestTemplate restTemplate, @Qualifier("serializingObjectMapper") ObjectMapper mapper) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
    }

    @Override
    public List<MalItem> findByWord(String keyword) {
        List<MalItem> beanList = new ArrayList<>();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(malUrl)
                .queryParam("type", "anime")
                .queryParam("keyword", keyword)
                .queryParam("v", 1);

        String res = restTemplate.getForObject(builder.toUriString(), String.class);
        JSONObject jsonObject = new JSONObject(res);

        String category = jsonObject
                .getJSONArray("categories")
                .getJSONObject(0)
                .get("items").toString();

        JavaType customClassCollection = mapper.getTypeFactory().constructCollectionType(List.class, MalItem.class);
        try {
            beanList = mapper.readValue(category, customClassCollection);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return beanList;
    }
}
