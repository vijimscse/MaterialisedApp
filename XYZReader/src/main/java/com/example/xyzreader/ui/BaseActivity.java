package com.example.xyzreader.ui;

import android.support.annotation.LayoutRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xyzreader.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sanchit.Agarwal on 6/20/2017.
 */

public class BaseActivity extends AppCompatActivity {

    ViewGroup mViewContainer;

    @BindView(R.id.back_btn)
    ImageView mBackBtn;

    @BindView(R.id.activityTitle)
    TextView mActivityTitle;

    @BindView(R.id.appBarLayout)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.collapse_toolbar)
    CollapsingToolbarLayout mCollapseToolBar;

    @BindView(R.id.banner_image)
    ImageView mLayoutUpperHalfView;

    @BindView(R.id.share_fab)
    FloatingActionButton mShareFAB;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(R.layout.activity_base);
        mViewContainer = (FrameLayout) findViewById(R.id.base_layout_container);

        if (mViewContainer != null && layoutResID != 0) {
            getLayoutInflater().inflate(layoutResID, mViewContainer);
        }
        ButterKnife.bind(this);
    }

    protected void setActivityTitle(String activityTitle) {
        mActivityTitle.setText(activityTitle);
    }

    protected void hideBanner() {
        mLayoutUpperHalfView.setVisibility(View.GONE);
    }

    protected void showBanner() {
        mLayoutUpperHalfView.setVisibility(View.VISIBLE);
    }

    protected void showBackBtn() {
        mBackBtn.setVisibility(View.VISIBLE);
    }

    protected void hideBackBtn() {
        mBackBtn.setVisibility(View.GONE);
    }

    protected void hideToolBar() {
        mCollapseToolBar.setVisibility(View.GONE);
    }

    protected void showToolBar() {
        mCollapseToolBar.setVisibility(View.VISIBLE);
    }

    @OnClick (R.id.back_btn)
    public void onBack() {
        onBackPressed();
    }

    protected void hideShareFAB() {
        mShareFAB.setVisibility(View.GONE);
    }

    protected void showShareFAB() {
        mShareFAB.setVisibility(View.VISIBLE);
    }
}

