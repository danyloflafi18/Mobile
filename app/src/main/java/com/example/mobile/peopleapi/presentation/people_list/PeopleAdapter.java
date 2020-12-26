package com.example.mobile.peopleapi.presentation.people_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile.R;
import com.example.mobile.peopleapi.presentation.listener.OnNewsListener;
import com.example.mobile.peopleapi.presentation.ui_data.NewsViewData;

import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleViewHolder>{

    private final List<NewsViewData> userList;
    private final OnNewsListener onNewsListener;

    public PeopleAdapter(List<NewsViewData> userList, OnNewsListener onNewsListener) {
        this.userList = userList;
        this.onNewsListener = onNewsListener;
    }

    public void setItems(List<NewsViewData> userList){
        this.userList.clear();
        this.userList.addAll(userList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_item, viewGroup, false);
        return new PeopleViewHolder(itemView, onNewsListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder viewHolder, int position) {
        viewHolder.bindTo(userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
