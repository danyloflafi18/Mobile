package com.example.mobile.peopleapi.presentation.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobile.peopleapi.domain.use_cases.LoadNewsUseCase;
import com.example.mobile.peopleapi.presentation.mapper.NewsItemMapper;
import com.example.mobile.peopleapi.presentation.ui_data.NewsViewData;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class NewsItemViewModel extends ViewModel {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<NewsViewData> newsItem = new MutableLiveData<>();

    private final LoadNewsUseCase loadNewsUseCase;

    public NewsItemViewModel(LoadNewsUseCase loadNewsUseCase) {
        this.loadNewsUseCase = loadNewsUseCase;
    }

    public void loadSpecificNewsItem(String author) {
        NewsItemMapper mapper = new NewsItemMapper(author);
        compositeDisposable.add(
                loadNewsUseCase.loadNews()
                        .map(mapper)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                newsItem::setValue,
                                error -> {
                                    Timber.e(error);
                                    errorMessage.setValue(error.getMessage());
                                }
                        )
        );
    }

    public MutableLiveData<NewsViewData> getNewsItem() {
        return newsItem;
    }
}
