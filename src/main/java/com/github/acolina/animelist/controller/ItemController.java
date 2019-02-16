package com.github.acolina.animelist.controller;

import com.github.acolina.animelist.model.entity.Item;
import com.github.acolina.animelist.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemService service;

    @Autowired
    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<List<Item>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

}
