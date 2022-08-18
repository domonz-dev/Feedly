package com.domonz.swipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {


    RecyclerView rec_feeds;
    ArrayList<FeedModel> feeds;
    ArrayList<String> feedObjects;
    int feedsSize = 4;
    ApiInterface apiService;
    ArrayList<Call<Object>> apis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void addAPIToList(){
        apis = new ArrayList<>();
        apis.add(apiService.getBBC());
        apis.add(apiService.getNewsWeek());
        apis.add(apiService.getCNN());
        apis.add(apiService.getWashingtonPost());

    }

    private void initialize(){
        apiService =
                ApiClient.getClient().create(ApiInterface.class);
        rec_feeds = findViewById(R.id.rec_feeds);
        feedObjects = new ArrayList<>();
        feeds = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rec_feeds.setLayoutManager(layoutManager);
        addAPIToList();

        for(int i = 0; i < apis.size(); i++){
            getFeeds(i);
        }
    }

    private void getFeedsAt(String i){

        try {
            JSONObject p_obj = new JSONObject(i);
            JSONArray items_arr = p_obj.getJSONArray("items");

            for(int j = 0; j < items_arr.length(); j++){
                JSONObject obj = items_arr.getJSONObject(j);
                String title = obj.getString("title");
                //String imageUrl = obj.getJSONObject("visual").getString("url");
                String category = p_obj.getString("title");
                String webName = p_obj.getString("title");
                String webUrl = obj.getJSONObject("origin").getString("htmlUrl");
                float pop = Float.parseFloat(!obj.has("engagement")?"0.0": obj.getString("engagement"));
                FeedModel fm = new FeedModel(title, category, "", webName, webUrl, pop);
                feeds.add(fm);
            }


            feeds.sort(new Comparator<FeedModel>() {
                @Override
                public int compare(FeedModel feedModel, FeedModel t1) {
                    return (int) (t1.popularity - feedModel.popularity);
                }
            });
            AdapterFeeds adapterFeeds = new AdapterFeeds(this, feeds);
            rec_feeds.setAdapter(adapterFeeds);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void getFeeds(int pos){
        Call<Object> call = apis.get(pos);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(@NonNull Call<Object> call, @NonNull Response<Object> response) {
                String js = new Gson().toJson(response.body());
                getFeedsAt(js);
                //feedObjects.add(js);
            }

            @Override
            public void onFailure(@NonNull Call<Object> call, Throwable t) {
                Log.e("TAG", "onFailure: "+t.toString() );
            }
        });

    }

}