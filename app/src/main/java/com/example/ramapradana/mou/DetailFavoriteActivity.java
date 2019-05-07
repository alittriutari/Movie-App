package com.example.ramapradana.mou;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ramapradana.mou.Reviews.ApiReview;
import com.example.ramapradana.mou.Reviews.ReviewAdapter;
import com.example.ramapradana.mou.Trailer.ApiTrailer;
import com.example.ramapradana.mou.data.local.DatabaseHelper;
import com.example.ramapradana.mou.data.local.MovieDatabase;
import com.example.ramapradana.mou.data.local.MovieEntity;
import com.example.ramapradana.mou.data.remote.MovieApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailFavoriteActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE_ITEM = "MOVIE_ITEM";
    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/";

    @BindView(R.id.iv_backdrop)
    ImageView ivBackdrop;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_poster)
    ImageView ivPoster;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_release)
    TextView tvRelease;
    @BindView(R.id.btn_fav)
    ImageView btnFav;
    @BindView(R.id.tv_overview)
    TextView tvOverview;
    @BindView(R.id.rv_trailer)
    RecyclerView rvTrailer;
    @BindView(R.id.rv_review)
    RecyclerView rvReview;

    Context context = DetailFavoriteActivity.this;
    private Call<ApiTrailer> call;
    private Call<ApiReview> callReview;
    private TrailerAdapter adapter;
    private ReviewAdapter adapterReview;
    private com.example.ramapradana.mou.Reviews.ResultsItem reviewItem;
    private MovieEntity resultsItem;
    private MovieDatabase movieDatabase;
    private MovieEntity movieById;


    public static void start(Context context, MovieEntity resultsItem) {
        Intent intent = new Intent(context, DetailFavoriteActivity.class);
        intent.putExtra(EXTRA_MOVIE_ITEM, resultsItem);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_favorite);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        resultsItem = getIntent().getParcelableExtra(EXTRA_MOVIE_ITEM);

        Glide.with(context)
                .load(IMAGE_BASE_URL+resultsItem.getBackdropPath())
                .into(ivBackdrop);

        Glide.with(context)
                .load(IMAGE_BASE_URL+resultsItem.getPosterPath())
                .into(ivPoster);

        tvTitle.setText(resultsItem.getTitle());
        tvRelease.setText(resultsItem.getReleaseDate());
        tvOverview.setText(resultsItem.getOverview());
        setTitle(resultsItem.getTitle());

        adapter = new TrailerAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvTrailer.setLayoutManager(linearLayoutManager);
        rvTrailer.setAdapter(adapter);

        adapterReview = new ReviewAdapter();

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvReview.setLayoutManager(linearLayoutManager1);
        rvReview.setAdapter(adapterReview);

        loadData();

        movieDatabase = DatabaseHelper.getMovieDatabase(getApplicationContext());
        movieById = movieDatabase.movieItemDao().getById(resultsItem.getId());
        if (resultsItem.isFavorite()){
            btnFav.setImageResource(R.drawable.ic_action_fav_full);
        }else{
            btnFav.setImageResource(R.drawable.ic_action_fav_border);
        }

        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean fav = false;
                if (movieById == null){
                    ResultsItem newResultItem = new ResultsItem(resultsItem);
                    MovieEntity newMovieEntity = new MovieEntity(newResultItem);
                    newMovieEntity.setFavorite(true);
                    movieDatabase.movieItemDao().insert(newMovieEntity);
                    fav = true;
                }else{
                    fav = !movieById.isFavorite();
                    movieById.setFavorite(fav);
                    movieDatabase.movieItemDao().update(movieById);
                }

                if (fav){
                    Snackbar.make(view, resultsItem.getTitle() +  " is now your favorite", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    btnFav.setImageResource(R.drawable.ic_action_fav_full);
                }else{
                    Snackbar.make(view, resultsItem.getTitle() + " is now removed from favorite", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    btnFav.setImageResource(R.drawable.ic_action_fav_border);
                }
            }
        });

    }

    public void loadData(){
        call = MovieApiClient.getMovieTrailerApiService()
                .getMovieTrailer(resultsItem.getId(), "e419dc0abebb5664696a5062f07f7f04");

        callReview = MovieApiClient.getMovieReviewApiService()
                .getMovieReview(resultsItem.getId(), "e419dc0abebb5664696a5062f07f7f04", "1");

        call.enqueue(new Callback<ApiTrailer>() {
            @Override
            public void onResponse(Call<ApiTrailer> call, Response<ApiTrailer> response) {
                if(response.isSuccessful()){
                    adapter.setData(response.body().getResults());
                }else{
                    Toast.makeText(context, "No Vid", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ApiTrailer> call, Throwable t) {
                Log.d("TRAILER", "onFailure: ", t);
            }
        });

        callReview.enqueue(new Callback<ApiReview>() {
            @Override
            public void onResponse(Call<ApiReview> call, Response<ApiReview> response) {
                if (response.isSuccessful() && response.body() != null){
                    adapterReview.setData(response.body().getResults());
                }else{
                    Toast.makeText(context, "No Review", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiReview> call, Throwable t) {

            }
        });
    }

}
