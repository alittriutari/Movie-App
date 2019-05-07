package com.example.ramapradana.mou.data.local;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by Rama Pradana on 2/14/2018.
 */

public class DatabaseHelper {
    private static final String DATABASE_NAME = "movie_database";
    private static MovieDatabase INSTANCE;

    public static MovieDatabase getMovieDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, MovieDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;
    }
}
