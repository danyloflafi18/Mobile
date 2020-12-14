package com.example.mobile.peopleapi.domain.repository;

import com.example.mobile.peopleapi.domain.entity.Example;

import io.reactivex.Single;

public interface IRepository {

    Single<Example> loadUsers();

}
