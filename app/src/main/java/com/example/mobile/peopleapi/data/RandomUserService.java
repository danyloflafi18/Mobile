package com.example.mobile.peopleapi.data;

import com.example.mobile.peopleapi.domain.entity.Example;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RandomUserService {

    @GET("top-headlines")
    Single<Example> loadUsers(@Query("sources") String source,
                              @Query("apiKey") String apiKey);
}
