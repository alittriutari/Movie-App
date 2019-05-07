package com.example.ramapradana.mou.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Rama Pradana on 2/14/2018.
 */

@Database(entities = {MovieEntity.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieItemDao movieItemDao();
}
