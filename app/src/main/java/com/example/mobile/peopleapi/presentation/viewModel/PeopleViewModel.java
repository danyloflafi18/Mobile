package com.example.mobile.peopleapi.presentation.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobile.peopleapi.domain.entity.Article;
import com.example.mobile.peopleapi.domain.use_cases.LoadUserUseCase;
import com.example.mobile.peopleapi.presentation.ui_data.UserViewData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class PeopleViewModel extends ViewModel {

    private final LoadUserUseCase loadUserUseCase;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<List<UserViewData>> responseData = new MutableLiveData<>();

    public PeopleViewModel(LoadUserUseCase loadUserUseCase) {
        this.loadUserUseCase = loadUserUseCase;
    }

    public void loadUserList() {
        compositeDisposable.add(
        loadUserUseCase.loadUser()
                .map(data -> {
                    List<UserViewData> result = new ArrayList<>();
                    for (Article item : data.getArticles()) {
                        UserViewData userViewData = new UserViewData(
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

    public MutableLiveData<List<UserViewData>> getResponseData() {
        return responseData;
    }

    @Override
    protected void onCleared(){
        super.onCleared();

        compositeDisposable.dispose();
    }
}
