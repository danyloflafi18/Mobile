package com.example.mobile.peopleapi.presentation.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobile.peopleapi.data.repository.RemoteRepository;
import com.example.mobile.peopleapi.domain.repository.IRepository;
import com.example.mobile.peopleapi.domain.use_cases.LoadUserUseCase;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final IRepository iRepository = new RemoteRepository();
    private final LoadUserUseCase loadUserUseCase = new LoadUserUseCase(iRepository);


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PeopleViewModel(loadUserUseCase);
    }
}
