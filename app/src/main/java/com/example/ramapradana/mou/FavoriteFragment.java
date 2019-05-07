package com.example.ramapradana.mou;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ramapradana.mou.data.local.DatabaseHelper;
import com.example.ramapradana.mou.data.local.MovieDatabase;
import com.example.ramapradana.mou.data.local.MovieEntity;

import java.util.List;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {


    FavoriteAdapter adapter;
    RecyclerView mFavoriteMovie;
    private MovieDatabase movieDatabase;
    private List<MovieEntity> movieById;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        mFavoriteMovie = view.findViewById(R.id.rv_favorite);

        adapter = new FavoriteAdapter();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, VERTICAL, false);
        mFavoriteMovie.setLayoutManager(gridLayoutManager);
        mFavoriteMovie.setAdapter(adapter);

        movieDatabase = DatabaseHelper.getMovieDatabase(getActivity().getApplicationContext());
        movieById = movieDatabase.movieItemDao().getByFavorite(true);

        adapter.setData(movieById);
        mFavoriteMovie.setLayoutManager(gridLayoutManager);
        mFavoriteMovie.setAdapter(adapter);

        return view;
    }

}
