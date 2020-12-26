package com.example.mobile.peopleapi.presentation.people_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile.R;
<<<<<<< HEAD
import com.example.mobile.peopleapi.presentation.clickListener.OnNewsListener;
import com.example.mobile.peopleapi.presentation.ui_data.UserViewData;

=======
import com.example.mobile.peopleapi.presentation.ui_data.UserViewData;

import java.util.ArrayList;
>>>>>>> 0359155fb3caa93418ca357293a32c18d26c9231
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleViewHolder>{

<<<<<<< HEAD
    private final List<UserViewData> userList;
    private final OnNewsListener onNewsListener;

    public PeopleAdapter(List<UserViewData> userList, OnNewsListener onNewsListener) {
        this.userList = userList;
        this.onNewsListener = onNewsListener;
    }
=======
    private final List<UserViewData> userList = new ArrayList<>();
>>>>>>> 0359155fb3caa93418ca357293a32c18d26c9231

    public void setItems(List<UserViewData> userList){
        this.userList.clear();
        this.userList.addAll(userList);
        notifyDataSetChanged();
<<<<<<< HEAD
=======

>>>>>>> 0359155fb3caa93418ca357293a32c18d26c9231
    }

    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_item, viewGroup, false);
<<<<<<< HEAD
        return new PeopleViewHolder(itemView, onNewsListener);
=======
        return new PeopleViewHolder(itemView);
>>>>>>> 0359155fb3caa93418ca357293a32c18d26c9231
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
