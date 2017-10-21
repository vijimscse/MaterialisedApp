package com.example.xyzreader.ui;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.text.Html;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xyzreader.R;
import com.example.xyzreader.data.ArticleLoader;
import com.example.xyzreader.data.ItemsContract;
import com.squareup.picasso.Picasso;

/**
 * Created by Vijayalakshmi on 9/17/2017.
 */
public class ArticleListAdapter extends CursorRecyclerViewAdapter<ArticleItemViewHolder> {
   private Context mContext;

    public ArticleListAdapter(Context context, Cursor cursor) {
        super(cursor);

        mContext = context;
    }

    @Override
    public ArticleItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item_article, parent, false);
        final ArticleItemViewHolder viewHolder = new ArticleItemViewHolder(view);

        viewHolder.thumbnailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityOptions options = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    options = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, viewHolder.thumbnailView,
                            mContext.getString(R.string.picture_transition_name));
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            ItemsContract.Items.buildItemUri(getItemId(viewHolder.getAdapterPosition())));
                    mContext.startActivity(intent, options.toBundle());
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ArticleItemViewHolder viewHolder, Cursor cursor) {
        viewHolder.titleView.setText(cursor.getString(ArticleLoader.Query.TITLE));
        viewHolder.subtitleView.setText(Html.fromHtml(DateUtils.getRelativeTimeSpanString(
                cursor.getLong(ArticleLoader.Query.PUBLISHED_DATE),
                System.currentTimeMillis(), DateUtils.HOUR_IN_MILLIS,
                DateUtils.FORMAT_ABBREV_ALL).toString()
                + cursor.getString(ArticleLoader.Query.AUTHOR)));
        Picasso.with(mContext).load(cursor.getString(ArticleLoader.Query.THUMB_URL)).into(viewHolder.thumbnailView);

        viewHolder.thumbnailView.setTag(cursor.getPosition());
    }
}
