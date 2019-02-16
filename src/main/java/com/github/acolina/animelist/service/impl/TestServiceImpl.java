package com.github.acolina.animelist.service.impl;

import com.github.acolina.animelist.core.exception.EntityNotFoundException;
import com.github.acolina.animelist.core.exception.EntityValidatedException;
import com.github.acolina.animelist.model.entity.Anime;
import com.github.acolina.animelist.model.entity.Item;
import com.github.acolina.animelist.model.entity.Test;
import com.github.acolina.animelist.model.request.TestRequestDto;
import com.github.acolina.animelist.model.response.TestResponseDto;
import com.github.acolina.animelist.repository.TestRepository;
import com.github.acolina.animelist.service.AnimeService;
import com.github.acolina.animelist.service.ItemService;
import com.github.acolina.animelist.service.TestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TestServiceImpl implements TestService {

    private final TestRepository repository;
    private final ItemService itemService;
    private final AnimeService animeService;
    private final Validator validator;
    private final ModelMapper mapper;

    @Autowired
    public TestServiceImpl(TestRepository repository, Validator validator, ModelMapper mapper, ItemService itemService,
                           AnimeService animeService) {
        this.repository = repository;
        this.validator = validator;
        this.mapper = mapper;
        this.itemService = itemService;
        this.animeService = animeService;
    }

    public List<TestResponseDto> findAll() {
        return map(repository.findAll());
    }

    public TestResponseDto save(TestRequestDto s) throws EntityValidatedException {

        Set<ConstraintViolation<Object>> error = validator.validate(s);
        if (!error.isEmpty()) {
            throw new EntityValidatedException(error);
        }
        Test t = mapToTest(s);
        Optional<Item> optional = itemService.findById(s.getItemId());
        Item i = optional.orElseThrow(() -> EntityNotFoundException.createInstance(s.getItemId()));
        t.setItem(i);

        if (!s.isOther()) {
            Optional<Anime> optionalAnime = animeService.findById(s.getAnime().getId());
            Anime a = optionalAnime.orElse(animeService.save(s.getAnime()));
            t.setAnime(a);
        }

        return map(repository.save(t));
    }

    public TestResponseDto findById(Long aLong) {
        return map(repository.findById(aLong).orElseThrow(() -> EntityNotFoundException.createInstance(aLong)));
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


    private List<TestResponseDto> map(List<Test> item) {
        return item.stream().map(e -> mapper.map(e, TestResponseDto.class)).collect(Collectors.toList());
    }

    private Test mapToTest(TestRequestDto item) {
        return mapper.map(item, Test.class);
    }

    private TestResponseDto map(Test item) {
        return mapper.map(item, TestResponseDto.class);
    }
}
