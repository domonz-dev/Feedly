package com.domonz.swipe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;

public class AdapterFeeds extends RecyclerView.Adapter<AdapterFeeds.ViewHolder> {

    Context context;
    ArrayList<FeedModel> feeds;

    public AdapterFeeds(Context context, ArrayList<FeedModel> feeds) {
        this.context = context;
        this.feeds = feeds;
    }

    @NonNull
    @Override
    public AdapterFeeds.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false));
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull AdapterFeeds.ViewHolder holder, int position) {

        FeedModel fm = feeds.get(position);

        Glide.with(context).load(fm.getImgUrl()).thumbnail(0.5f).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.image);
        holder.title.setText( Html.fromHtml("<font color='#AAAAAA'>"+ fm.getWebName()+ "</font>") + " | " +fm.getTitle());
        holder.popularity.setText((int) fm.getPopularity()+"");
        try {
            holder.web.setText(getDomainName(fm.getWebUrl()));
        } catch (URISyntaxException e) {
            holder.web.setText(fm.getWebName());
            e.printStackTrace();
        }

        holder.web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(fm.getWebUrl()));
                context.startActivity(browserIntent);
            }
        });


    }

    public static String getDomainName(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title, web, popularity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.f_image);
            title = itemView.findViewById(R.id.f_title);
            popularity = itemView.findViewById(R.id.f_popularity);
            web = itemView.findViewById(R.id.f_web);
        }
    }
}
