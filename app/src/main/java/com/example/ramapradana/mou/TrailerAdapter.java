package com.example.ramapradana.mou;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ramapradana.mou.Trailer.*;
import com.example.ramapradana.mou.Trailer.ResultsItem;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

/**
 * Created by Rama Pradana on 2/13/2018.
 */

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {

    private static final String YOUTUBE_WATCH_URL = "https://www.youtube.com/watch?v=";
    private static final String YOUTUBE_TUMBNAIL_URL = "https://img.youtube.com/vi/";
    private static final String IMAGE_QUALITY = "/sddefault.jpg";
    private List<com.example.ramapradana.mou.Trailer.ResultsItem> dataSet = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_trailer, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


            ResultsItem trailerItem = dataSet.get(position);

            Glide.with(holder.itemView.getContext())
                    .load(YOUTUBE_TUMBNAIL_URL+trailerItem.getKey()+IMAGE_QUALITY)
                    .into(holder.mVideoImage);

            holder.mTrailerTitle.setText(trailerItem.getName());

            holder.mItemTrailer.setOnClickListener(new View.OnClickListener() {

                Context context = holder.itemView.getContext();
                @Override
                public void onClick(View view) {
                    Uri webpage = Uri.parse(YOUTUBE_WATCH_URL+trailerItem.getKey());
                    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                    if (intent.resolveActivity(context.getPackageManager()) != null) {
                        context.startActivity(intent);
                    }
                }
            });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void setData(List<ResultsItem> trailerItems){
        this.dataSet = trailerItems;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView mVideoImage;
        TextView mTrailerTitle;
        LinearLayout mItemTrailer;

        public ViewHolder(View itemView) {
            super(itemView);
            mVideoImage = itemView.findViewById(R.id.img_video_image);
            mTrailerTitle = itemView.findViewById(R.id.tv_video_title);
            mItemTrailer = itemView.findViewById(R.id.linear_trailer);
        }
    }
}
