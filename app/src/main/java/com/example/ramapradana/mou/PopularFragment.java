package com.example.ramapradana.mou;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ramapradana.mou.data.remote.ApiResponse;
import com.example.ramapradana.mou.data.remote.MovieApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.v7.widget.GridLayoutManager.*;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment {


    private RecyclerView rvMovie;
    private MoviePopularAdapter adapter;
    private SwipeRefreshLayout mSwipeRefresh;
    private int page = 1;


    private Call<ApiResponse> call;


    public PopularFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_popular, container, false);
        rvMovie=view.findViewById(R.id.rv_movie);
        mSwipeRefresh = view.findViewById(R.id.sr_swipe_refresh);

        mSwipeRefresh.setOnRefreshListener(() -> loadData());;

        adapter = new MoviePopularAdapter();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, VERTICAL, false);
        rvMovie.setLayoutManager(gridLayoutManager);
        rvMovie.setAdapter(adapter);

        loadData();

        rvMovie.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount        = gridLayoutManager.getChildCount();
                int totalItemCount          = gridLayoutManager.getItemCount();
                int firstVisibleItemPosition= gridLayoutManager.findFirstVisibleItemPosition();

                // Load more if we have reach the end to the recyclerView
                if ( (visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                    if (isOnline()){

                        call = MovieApiClient.getMoviePopularApiService()
                                .getPopularMovie("e419dc0abebb5664696a5062f07f7f04", String.valueOf(page));

                        call.enqueue(new Callback<ApiResponse>() {
                            @Override
                            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                                adapter.addData(response.body().getResults());
                                mSwipeRefresh.setRefreshing(false);
                                page++;
                            }

                            @Override
                            public void onFailure(Call<ApiResponse> call, Throwable t) {
                                call.cancel();
                                Log.d("Error Req", "onFailure: ", t);
                                Toast.makeText(getContext(), "You are not connected to the internet", Toast.LENGTH_SHORT).show();
                                mSwipeRefresh.setRefreshing(false);
                            }
                        });
                    }else{
                        Toast.makeText(getContext(), "You are not connected to the internet", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }


    protected boolean isOnline() {

        Context cn = getContext();

        ConnectivityManager cm = (ConnectivityManager) cn.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()) {

            return true;

        } else {

            return false;

        }

    }

    public void loadData(){

        mSwipeRefresh.setRefreshing(true);
        if (isOnline()){

            call = MovieApiClient.getMoviePopularApiService()
                    .getPopularMovie("e419dc0abebb5664696a5062f07f7f04", String.valueOf(page));

            call.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    adapter.setData(response.body().getResults());
                    mSwipeRefresh.setRefreshing(false);
                    page++;
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    call.cancel();
                    Log.d("Error Req", "onFailure: ", t);
                    Toast.makeText(getContext(), "You are not connected to the internet", Toast.LENGTH_SHORT).show();
                    mSwipeRefresh.setRefreshing(false);
                }
            });
        }else{
            Toast.makeText(getContext(), "You are not connected to the internet", Toast.LENGTH_SHORT).show();
        }

    }

}
