package com.example.rss_reader.Activities.RetrofitConf;

import com.example.rss_reader.Activities.DTO.RssFeed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RssService {
    @GET("rss/{rssName}")
    Call<RssFeed>getAllFeed(@Path("rssName") String rssName);
}
