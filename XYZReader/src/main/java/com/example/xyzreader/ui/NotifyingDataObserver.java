package com.example.xyzreader.ui;

import android.database.DataSetObserver;

/**
 * Created by VijayaLakshmi.IN on 21-10-2017.
 */
public class NotifyingDataObserver extends DataSetObserver {

    private CursorRecyclerViewAdapter mCursorRecyclerViewAdapter;

    public NotifyingDataObserver(CursorRecyclerViewAdapter adapter) {
        mCursorRecyclerViewAdapter = adapter;
    }

    @Override
    public void onChanged() {
        super.onChanged();
        if (mCursorRecyclerViewAdapter != null) {
            mCursorRecyclerViewAdapter.setataIsValid(true);
            mCursorRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onInvalidated() {
        super.onInvalidated();
        if (mCursorRecyclerViewAdapter != null) {
            mCursorRecyclerViewAdapter.setataIsValid(false);
            mCursorRecyclerViewAdapter.notifyDataSetChanged();
        }
    }
}