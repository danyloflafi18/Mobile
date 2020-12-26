package com.example.mobile.peopleapi.presentation.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobile.peopleapi.domain.entity.Article;
import com.example.mobile.peopleapi.domain.use_cases.LoadNewsUseCase;
import com.example.mobile.peopleapi.presentation.ui_data.NewsViewData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class NewsViewModel extends ViewModel {

    private final LoadNewsUseCase loadUserUseCase;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<List<NewsViewData>> responseData = new MutableLiveData<>();

    public NewsViewModel(LoadNewsUseCase loadUserUseCase) {
        this.loadUserUseCase = loadUserUseCase;
    }

    public void loadUserList() {
        compositeDisposable.add(
        loadUserUseCase.loadNews()
                .map(data -> {
                    List<NewsViewData> result = new ArrayList<>();
                    for (Article item : data.getArticles()) {
                        NewsViewData userViewData = new NewsViewData(
                                item.getAuthor(),
                                item.getTitle(),
                                item.getPublishedAt(),
                                item.getUrlToImage());
                        result.add(userViewData);
                    }
                    return result;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        responseData::setValue,
                        error -> {
                            Timber.e("loadUserList: %s", error.getLocalizedMessage());
                            errorMessage.setValue(error.getMessage());
                        }
                )
        );
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public MutableLiveData<List<NewsViewData>> getResponseData() {
        return responseData;
    }

    @Override
    protected void onCleared(){
        super.onCleared();
        compositeDisposable.dispose();
    }
}
