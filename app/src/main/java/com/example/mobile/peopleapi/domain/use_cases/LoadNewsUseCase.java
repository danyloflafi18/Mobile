package com.example.mobile.peopleapi.domain.use_cases;

import com.example.mobile.peopleapi.domain.entity.Example;
import com.example.mobile.peopleapi.domain.repository.IRepository;

import io.reactivex.Single;

public class LoadNewsUseCase {

    private final IRepository repository;

    public LoadNewsUseCase(IRepository repository) {
        this.repository = repository;
    }

    public Single<Example> loadNews() {
        return repository.loadNews();
    }
}
