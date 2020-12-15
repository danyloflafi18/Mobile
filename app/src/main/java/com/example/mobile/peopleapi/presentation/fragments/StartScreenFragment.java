package com.example.mobile.peopleapi.presentation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile.R;
import com.example.mobile.peopleapi.presentation.PeopleViewModel;
import com.example.mobile.peopleapi.presentation.ViewModelFactory;
import com.example.mobile.peopleapi.presentation.people_list.PeopleAdapter;

public class StartScreenFragment extends Fragment {
    private RecyclerView userList;
    PeopleAdapter peopleAdapter = new PeopleAdapter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_screen, container, false);

        initRecycler(view);

        initViewModel();

        return view;
    }

    private void initViewModel() {
        PeopleViewModel peopleViewModel = new ViewModelProvider(this,
                new ViewModelFactory())
                .get(PeopleViewModel.class);
        peopleViewModel.getErrorMessage().observe(getViewLifecycleOwner(), errorMessage -> Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show());
        peopleViewModel.getResponseData().observe(getViewLifecycleOwner(), response -> peopleAdapter.setItems(response));
        peopleViewModel.loadUserList();
    }

    private void initRecycler(View view) {
        userList = view.findViewById(R.id.user_list);
        userList.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,
                false));
        userList.setAdapter(peopleAdapter);
    }

}