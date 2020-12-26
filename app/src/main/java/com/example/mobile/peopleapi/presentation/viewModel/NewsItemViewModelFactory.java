package com.example.mobile.peopleapi.presentation.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobile.peopleapi.data.repository.RemoteRepository;
import com.example.mobile.peopleapi.domain.repository.IRepository;
import com.example.mobile.peopleapi.domain.use_cases.LoadNewsUseCase;

public class NewsItemViewModelFactory implements ViewModelProvider.Factory{
    private final IRepository repository = new RemoteRepository();
    private final LoadNewsUseCase loadNewsUseCase = new LoadNewsUseCase(repository);

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new NewsItemViewModel(loadNewsUseCase);
    }
}
