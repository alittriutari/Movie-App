package com.example.ramapradana.mou.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

import com.example.ramapradana.mou.MovieItem;

import java.util.List;

import retrofit2.http.Query;

/**
 * Created by Rama Pradana on 2/14/2018.
 */
@Dao
public interface MovieItemDao {

    @android.arch.persistence.room.Query("SELECT * FROM MovieEntity")
    List<MovieEntity> getAll();

    @android.arch.persistence.room.Query("SELECT * FROM MovieEntity WHERE id = :id LIMIT 1")
    MovieEntity getById(int id);

    @android.arch.persistence.room.Query("SELECT * FROM MOVIEENTITY WHERE favorite = :favorite")
    List<MovieEntity> getByFavorite(boolean favorite);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MovieEntity movieEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<MovieEntity>movieEntities);

    @Update
    void update(MovieEntity movieEntity);

    @Delete
    void delete(MovieEntity movieEntity);

    @Delete
    void deleteAll(List<MovieEntity> movieEntities);
}
