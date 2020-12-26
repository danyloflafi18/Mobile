package com.example.mobile.peopleapi.data.repository;

import com.example.mobile.peopleapi.data.RandomUserService;
import com.example.mobile.peopleapi.domain.entity.Example;
import com.example.mobile.peopleapi.domain.repository.IRepository;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteRepository implements IRepository {
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static final String SOURCE = "techcrunch";
    private static final String API_KEY = "9cade7c61cd54c4e9f8f3ab8120d2282";

    private final RandomUserService service;

    public RemoteRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        service = retrofit.create(RandomUserService.class);
    }

    private OkHttpClient getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }

    @Override
    public Single<Example> loadUsers() {
        return service.loadUsers(SOURCE, API_KEY);
    }
}
