package com.github.acolina.animelist.service;

import com.github.acolina.animelist.core.exception.EntityValidatedException;
import com.github.acolina.animelist.model.request.TestRequestDto;
import com.github.acolina.animelist.model.response.TestResponseDto;

import java.util.List;

public interface TestService {

    List<TestResponseDto> findAll();

    TestResponseDto save(TestRequestDto s) throws EntityValidatedException;

    TestResponseDto findById(Long aLong);

    boolean existsById(Long aLong);

    long count();

    void deleteById(Long aLong);
}
