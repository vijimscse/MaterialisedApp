package com.example.xyzreader.remote;

import com.example.xyzreader.data.NewsFeed;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Vijayalakshmi.IN on 7/13/2017.
 */

public interface APIService {
    @GET(IOConstants.READ_FEEDS_JSON)
    Call<List<NewsFeed>> getNewsFeeds();
}

