package com.example.ramapradana.mou;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rama Pradana on 2/9/2018.
 */

public class MoviePopularAdapter extends RecyclerView.Adapter<MoviePopularAdapter.ViewHolder> {

        private static final String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500/";

    private List<ResultsItem> dataSet = new ArrayList<>();


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ResultsItem resultItem = dataSet.get(position);

        holder.mTextViewRating.setText(String.valueOf(resultItem.getPopularity()));
        holder.mTextViewDateRelease.setText(resultItem.getReleaseDate());
        holder.mTextViewTitle.setText(resultItem.getTitle());
        Glide.with(holder.itemView.getContext())
                .load(POSTER_BASE_URL + resultItem.getPosterPath())
                .into(holder.ivMovie);

        holder.mItemMovie.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DetailActivity.start(holder.itemView.getContext(), resultItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void setData(List<ResultsItem> movieItemList){
        this.dataSet = movieItemList;
        notifyDataSetChanged();
    }

    public void addData(List<ResultsItem> movieItemList){
        this.dataSet.addAll(movieItemList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivMovie;
        TextView mTextViewRating, mTextViewDateRelease, mTextViewTitle;
        LinearLayout mItemMovie;

        public ViewHolder(View itemView) {
            super(itemView);

            ivMovie = itemView.findViewById(R.id.img_movie);
            mTextViewDateRelease = itemView.findViewById(R.id.tv_date_release);
            mTextViewRating = itemView.findViewById(R.id.tv_rating);
            mTextViewTitle = itemView.findViewById(R.id.tv_title);
            mItemMovie = itemView.findViewById(R.id.lin_item_movie);
        }
    }
}
