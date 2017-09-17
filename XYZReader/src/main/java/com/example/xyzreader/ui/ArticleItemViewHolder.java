package com.example.xyzreader.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xyzreader.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Vijayalakshmi on 9/17/2017.
 */
public class ArticleItemViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.banner_image)
    ImageView thumbnailView;

    @BindView(R.id.article_title)
    public TextView titleView;

    @BindView(R.id.article_subtitle)
    public TextView subtitleView;

    public ArticleItemViewHolder(View view) {
        super(view);

        ButterKnife.bind(this, view);
    }
}