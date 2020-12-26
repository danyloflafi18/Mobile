package com.example.mobile.peopleapi.presentation.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mobile.R;
import com.example.mobile.peopleapi.presentation.activities.NewsActivity;
import com.example.mobile.peopleapi.presentation.clickListener.OnNewsListener;
import com.example.mobile.peopleapi.presentation.clickListener.OnProfileListener;
import com.example.mobile.peopleapi.presentation.people_list.PeopleAdapter;
import com.example.mobile.peopleapi.presentation.ui_data.UserViewData;
import com.example.mobile.peopleapi.presentation.viewModel.NewsViewModel;
import com.example.mobile.peopleapi.presentation.viewModel.ViewModelFactory;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StartScreenFragment extends Fragment implements OnNewsListener {
    private PeopleAdapter peopleAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private NewsViewModel newsViewModel;
    private ProgressBar loadingIndicator;
    private final List<UserViewData> userList = new ArrayList<>();
    private OnProfileListener onProfileListener;


    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);

        try {
            onProfileListener = (OnProfileListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnProfileListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onProfileListener = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_screen, container, false);

        initUI(view);

        initRecycler(view);

        initViewModel();

        return view;
    }

    private void initUI(View startScreenView) {
        swipeRefreshLayout = startScreenView.findViewById(R.id.startScreen);
        swipeRefreshLayout.setOnRefreshListener(() -> newsViewModel.loadUserList());

        loadingIndicator = startScreenView.findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.VISIBLE);
        Button myProfileButton = startScreenView.findViewById(R.id.my_profile_button);
        myProfileButton.setOnClickListener(view -> onProfileListener.onProfileClick());
    }

    private void initViewModel() {
        newsViewModel = new ViewModelProvider(this,
                new ViewModelFactory())
                .get(NewsViewModel.class);
        newsViewModel.getErrorMessage().observe(getViewLifecycleOwner(), errorMessage -> {
            Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
            hideLoading();
        });
        newsViewModel.getResponseData().observe(getViewLifecycleOwner(), response -> {
            peopleAdapter.setItems(response);
            hideLoading();
        });
        newsViewModel.loadUserList();
    }

    private void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
        loadingIndicator.setVisibility(View.GONE);
    }

    private void initRecycler(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.user_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        peopleAdapter = new PeopleAdapter(userList, this);
        recyclerView.setAdapter(peopleAdapter);
    }

    @Override
    public void onNewsClick(int position) {
        Intent intent = new Intent(getActivity(), NewsActivity.class);

        UserViewData userViewData = userList.get(position);
        intent.putExtra("urlToImage", userViewData.getUrlToImage());
        intent.putExtra("title", userViewData.getTitle());
        intent.putExtra("author", userViewData.getAuthor());
        intent.putExtra("date", userViewData.getPublishedAt());
        startActivity(intent);
    }
}