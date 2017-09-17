package com.example.xyzreader.ui;

import android.support.annotation.LayoutRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.xyzreader.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sanchit.Agarwal on 6/20/2017.
 */

public class BaseActivity extends AppCompatActivity {

    ViewGroup mViewContainer;

    @BindView(R.id.tvHeaderCenterTitle)
    ImageView mActivityTitle;

    @BindView(R.id.appBarLayout)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.collapse_toolbar)
    CollapsingToolbarLayout mCollapseToolBar;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(R.layout.activity_base);
        mViewContainer = (FrameLayout) findViewById(R.id.base_layout_container);

        if (mViewContainer != null && layoutResID != 0) {
            getLayoutInflater().inflate(layoutResID, mViewContainer);
        }
        ButterKnife.bind(this);

    }

    /*protected void setActivityTitle(String activityTitle) {
        mActivityTitle.setText(activityTitle);
    }*/

    protected void hideToolBar() {
        mCollapseToolBar.setVisibility(View.GONE);
    }

    protected void showToolBar() {
        mCollapseToolBar.setVisibility(View.VISIBLE);
    }

}

