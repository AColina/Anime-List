package com.github.acolina.animelist.controller;

import com.github.acolina.animelist.model.mal.MalItem;
import com.github.acolina.animelist.model.response.ResponseDTO;
import com.github.acolina.animelist.service.MyAnimeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mal")
public class MyAnimeListController {

    private final MyAnimeListService service;

    @Autowired
    public MyAnimeListController(MyAnimeListService service) {
        this.service = service;
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<ResponseDTO<List<MalItem>>> getAnime(@RequestParam("keyword") String keyword) {
        try {
            List<MalItem> malItems = service.findByWord(keyword);
            return ResponseDTO.ok(malItems);
        } catch (Exception e) {
            return ResponseDTO.error(e);
        }
    }
}
