package com.example.ramapradana.mou;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ramapradana.mou.data.local.MovieEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rama Pradana on 2/14/2018.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>{

    private List<MovieEntity> favoriteMovie = new ArrayList<>();
    private static final String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500/";

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieEntity favMov = favoriteMovie.get(position);

        holder.mTextViewRating.setText(String.valueOf(favMov.getPopularity()));
        holder.mTextViewDateRelease.setText(favMov.getReleaseDate());
        holder.mTextViewTitle.setText(favMov.getTitle());

        Glide.with(holder.itemView.getContext())
                .load(POSTER_BASE_URL+favMov.getPosterPath())
                .into(holder.ivMovie);

        holder.mItemMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailFavoriteActivity.start(holder.itemView.getContext(), favMov);
            }
        });

    }

    @Override
    public int getItemCount() {
        return favoriteMovie.size();
    }

    public void setData(List<MovieEntity> favoriteMovie){
        this.favoriteMovie =  favoriteMovie;
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
