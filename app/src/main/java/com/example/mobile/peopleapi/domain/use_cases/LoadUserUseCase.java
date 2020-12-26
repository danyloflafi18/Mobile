package com.example.mobile.peopleapi.domain.use_cases;

import com.example.mobile.peopleapi.domain.entity.Example;
import com.example.mobile.peopleapi.domain.repository.IRepository;

import io.reactivex.Single;

public class LoadUserUseCase {

    private final IRepository repository;

    public LoadUserUseCase(IRepository repository) {
        this.repository = repository;
    }

    public Single<Example> loadUser() {
        return repository.loadUsers();
    }
}
