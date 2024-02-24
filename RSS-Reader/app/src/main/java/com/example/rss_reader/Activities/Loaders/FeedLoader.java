package com.example.rss_reader.Activities.Loaders;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.rss_reader.Activities.DTO.RssFeed;
import com.example.rss_reader.Activities.RetrofitConf.RssService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class FeedLoader extends AsyncTaskLoader<RssFeed> {
    RssService rssService;
    String rssName;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://vnexpress.net/")
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build();
    public FeedLoader(@NonNull Context context, String rssName) {
        super(context);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://vnexpress.net/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        rssService = retrofit.create(RssService.class);
        this.rssName = rssName;
    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }
    @Nullable
    @Override
    public RssFeed loadInBackground() {
        try {
            Call<RssFeed> call = rssService.getAllFeed(rssName);
            Response<RssFeed> response = call.execute();

            if (response.isSuccessful()) {
                System.out.println(response);
                return response.body();
            } else {
                Log.e(TAG, "Failed to fetch RSS feed: " + response.errorBody());
            }
        } catch (IOException e) {
            Log.e(TAG, "Network error: " + e.getMessage(), e);
        }
        return null;
    }
}
