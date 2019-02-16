package com.github.acolina.animelist.controller;

import com.github.acolina.animelist.core.exception.EntityValidatedException;
import com.github.acolina.animelist.model.entity.Test;
import com.github.acolina.animelist.model.request.TestRequestDto;
import com.github.acolina.animelist.model.response.ResponseDTO;
import com.github.acolina.animelist.model.response.TestResponseDto;
import com.github.acolina.animelist.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService service;

    @Autowired
    public TestController(TestService service) {
        this.service = service;
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity<ResponseDTO<TestResponseDto>> save(@RequestBody TestRequestDto requestDto) {
        try {
            TestResponseDto clientDTO = service.save(requestDto);
            return ResponseDTO.ok(clientDTO);
        } catch (EntityValidatedException e) {
            return ResponseDTO.error(e);
        } catch (Exception e) {
            throw e;
        }
    }
}
