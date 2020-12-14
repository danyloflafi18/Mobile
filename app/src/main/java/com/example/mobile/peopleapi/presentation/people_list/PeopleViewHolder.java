package com.example.mobile.peopleapi.presentation.people_list;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobile.R;
import com.example.mobile.peopleapi.presentation.ui_data.UserViewData;

public class PeopleViewHolder extends RecyclerView.ViewHolder {
    private final TextView author;
    private final TextView title;
    private final TextView published_at;

    private final ImageView url_to_image;

    public PeopleViewHolder(@NonNull View itemView) {
        super(itemView);

        author = itemView.findViewById(R.id.author);
        title = itemView.findViewById(R.id.title);
        published_at = itemView.findViewById(R.id.published_at);

        url_to_image = itemView.findViewById(R.id.url_to_image);
    }

    protected void bindTo(UserViewData userViewData) {
        author.setText(userViewData.getAuthor());
        title.setText(userViewData.getTitle());
        published_at.setText(userViewData.getPublishedAt());

        Glide.with(url_to_image)
                .load(userViewData.getUrlToImage())
                .fitCenter()
                .into(url_to_image);
    }
}