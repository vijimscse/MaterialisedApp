package com.example.xyzreader.data;

import android.app.IntentService;
import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.RemoteException;
import android.text.format.Time;
import android.util.Log;

import com.example.xyzreader.remote.IOManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdaterService extends IntentService {
    private static final String TAG = "UpdaterService";

    public static final String BROADCAST_ACTION_STATE_CHANGE
            = "com.example.xyzreader.intent.action.STATE_CHANGE";
    public static final String EXTRA_REFRESHING
            = "com.example.xyzreader.intent.extra.REFRESHING";

    public UpdaterService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Time time = new Time();

        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null || !ni.isConnected()) {
            Log.w(TAG, "Not online, not refreshing.");
            return;
        }

        sendStickyBroadcast(
                new Intent(BROADCAST_ACTION_STATE_CHANGE).putExtra(EXTRA_REFRESHING, true));

        // Don't even inspect the intent, we only do one thing, and that's fetch content.
        final ArrayList<ContentProviderOperation> cpo = new ArrayList<ContentProviderOperation>();

        final Uri dirUri = ItemsContract.Items.buildDirUri();

        // Delete all items
        cpo.add(ContentProviderOperation.newDelete(dirUri).build());

        IOManager.requestNewsFeeds(new Callback<List<NewsFeed>>() {
            @Override
            public void onResponse(Call<List<NewsFeed>> call, Response<List<NewsFeed>> response) {
                Log.d(TAG, " Response : " + response.body());
                if (response != null && response.body() != null) {
                    List<NewsFeed> newsFeedList = response.body();
                    for (NewsFeed newsFeed : newsFeedList) {
                        ContentValues values = new ContentValues();
                        values.put(ItemsContract.Items.SERVER_ID, newsFeed.getId());
                        values.put(ItemsContract.Items.AUTHOR, newsFeed.getAuthor());
                        values.put(ItemsContract.Items.TITLE, newsFeed.getTitle());
                        values.put(ItemsContract.Items.BODY, newsFeed.getBody());
                        values.put(ItemsContract.Items.THUMB_URL, newsFeed.getThumb());
                        values.put(ItemsContract.Items.PHOTO_URL, newsFeed.getPhoto());
                        values.put(ItemsContract.Items.ASPECT_RATIO, newsFeed.getAspectRatio());
                        values.put(ItemsContract.Items.PUBLISHED_DATE, newsFeed.getPublishedDate());
                        cpo.add(ContentProviderOperation.newInsert(dirUri).withValues(values).build());
                    }
                }
                try {
                    getContentResolver().applyBatch(ItemsContract.CONTENT_AUTHORITY, cpo);
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (OperationApplicationException e) {
                    e.printStackTrace();
                }

                sendStickyBroadcast(
                        new Intent(BROADCAST_ACTION_STATE_CHANGE).putExtra(EXTRA_REFRESHING, false));
            }

            @Override
            public void onFailure(Call<List<NewsFeed>> call, Throwable t) {
                Log.d(TAG, " Error : " + t.getMessage());
                sendStickyBroadcast(
                        new Intent(BROADCAST_ACTION_STATE_CHANGE).putExtra(EXTRA_REFRESHING, false));
            }
        });
    }
}
