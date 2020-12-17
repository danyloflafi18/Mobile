package com.example.mobile.peopleapi.presentation.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mobile.R;
import com.example.mobile.peopleapi.presentation.SharedPrefs;
import com.example.mobile.peopleapi.presentation.activities.MyProfileActivity;
import com.example.mobile.peopleapi.presentation.activities.NewsActivity;
import com.example.mobile.peopleapi.presentation.people_list.OnNewsListener;
import com.example.mobile.peopleapi.presentation.people_list.PeopleAdapter;
import com.example.mobile.peopleapi.presentation.ui_data.UserViewData;
import com.example.mobile.peopleapi.presentation.viewModel.PeopleViewModel;
import com.example.mobile.peopleapi.presentation.viewModel.ViewModelFactory;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StartScreenFragment extends Fragment implements OnNewsListener {
    private PeopleAdapter peopleAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private PeopleViewModel peopleViewModel;
    private ProgressBar loadingIndicator;
    private final List<UserViewData> userList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NotNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem menuItem = menu.findItem(R.id.profile_image);
        View view = MenuItemCompat.getActionView(menuItem);

        CircleImageView profileImage = view.findViewById(R.id.custom_profile_image);
        profileImage.setOnClickListener(view1 -> Toast.makeText(getActivity(), "Profile Clicked", Toast.LENGTH_SHORT).show());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile_image:
                Intent intent = new Intent(getActivity(), MyProfileActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_screen, container, false);

        sharedPref();

        initUI(view);

        initRecycler(view);

        initViewModel();

        return view;
    }

    private void sharedPref() {
        SharedPrefs sharedPrefs = new SharedPrefs();
        sharedPrefs.initSharedPrefs(this);
        sharedPrefs.setUserName("Lucas Matney");
    }

    private void initUI(View view) {
        swipeRefreshLayout = view.findViewById(R.id.startScreen);
        swipeRefreshLayout.setOnRefreshListener(() -> peopleViewModel.loadUserList());

        loadingIndicator = view.findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.VISIBLE);
    }

    private void initViewModel() {
        peopleViewModel = new ViewModelProvider(this,
                new ViewModelFactory())
                .get(PeopleViewModel.class);
        peopleViewModel.getErrorMessage().observe(getViewLifecycleOwner(), errorMessage -> {
            Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
            hideLoading();
        });
        peopleViewModel.getResponseData().observe(getViewLifecycleOwner(), response -> {
            peopleAdapter.setItems(response);
            hideLoading();
        });
        peopleViewModel.loadUserList();
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