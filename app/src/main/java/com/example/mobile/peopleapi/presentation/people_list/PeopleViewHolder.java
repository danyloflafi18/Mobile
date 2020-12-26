package com.example.mobile.peopleapi.presentation.people_list;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobile.R;
import com.example.mobile.peopleapi.presentation.listener.OnNewsListener;
import com.example.mobile.peopleapi.presentation.ui_data.NewsViewData;

public class PeopleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final TextView author;
    private final TextView title;
    private final TextView published_at;
    private final ImageView url_to_image;

    private final OnNewsListener onNewsListener;

    public PeopleViewHolder(@NonNull View itemView, OnNewsListener onNewsListener) {
        super(itemView);

        author = itemView.findViewById(R.id.author);
        title = itemView.findViewById(R.id.title);
        published_at = itemView.findViewById(R.id.published_at);

        url_to_image = itemView.findViewById(R.id.url_to_image);

        this.onNewsListener = onNewsListener;
        itemView.setOnClickListener(this);
    }

    protected void bindTo(NewsViewData userViewData) {
        author.setText(userViewData.getAuthor());
        title.setText(userViewData.getTitle());
        published_at.setText(userViewData.getPublishedAt());

        Glide.with(url_to_image)
                .load(userViewData.getUrlToImage())
                .fitCenter()
                .into(url_to_image);
    }

    @Override
    public void onClick(View view) {
        onNewsListener.onNewsClick(getAdapterPosition());
    }
}