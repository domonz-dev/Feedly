package com.domonz.swipe;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("/v3/streams/contents?streamId=feed/http://rss.cnn.com/rss/cnn_topstories.rss")
    Call<Object> getCNN();

    @GET("/v3/streams/contents?streamId=feed/http://feeds.bbci.co.uk/news/world/rss.xml")
    Call<Object> getBBC();

    @GET("/v3/streams/contents?streamId=feed/https://www.newsweek.com/rss")
    Call<Object> getNewsWeek();

    @GET("/v3/streams/contents?streamId=feed/http://www.washingtonpost.com/rss/entertainment")
    Call<Object> getWashingtonPost();

}