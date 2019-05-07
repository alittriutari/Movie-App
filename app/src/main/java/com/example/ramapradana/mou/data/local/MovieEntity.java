package com.example.ramapradana.mou.data.local;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.example.ramapradana.mou.ResultsItem;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rama Pradana on 2/14/2018.
 */
@Entity
public class MovieEntity implements Parcelable{



    private String overview;
    private String originalLanguage;
    private String originalTitle;
    private boolean video;
    private String title;

    private String posterPath;

    private String backdropPath;

    private String releaseDate;
    private int voteAverage;
    private double popularity;
    @PrimaryKey
    @NonNull
    private int id;
    private boolean adult;
    private int voteCount;
    private boolean favorite;

    protected MovieEntity(Parcel in) {
        overview = in.readString();
        originalLanguage = in.readString();
        originalTitle = in.readString();
        video = in.readByte() != 0;
        title = in.readString();
        posterPath = in.readString();
        backdropPath = in.readString();
        releaseDate = in.readString();
        voteAverage = in.readInt();
        popularity = in.readDouble();
        id = in.readInt();
        adult = in.readByte() != 0;
        voteCount = in.readInt();
        favorite = in.readByte() != 0;
    }

    public static final Creator<MovieEntity> CREATOR = new Creator<MovieEntity>() {
        @Override
        public MovieEntity createFromParcel(Parcel in) {
            return new MovieEntity(in);
        }

        @Override
        public MovieEntity[] newArray(int size) {
            return new MovieEntity[size];
        }
    };

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public MovieEntity(){

    }

    public MovieEntity(ResultsItem resultsItem){
        this.id = resultsItem.getId();
        this.overview = resultsItem.getOverview();
        this.originalLanguage = resultsItem.getOriginalLanguage();
        this.originalLanguage = resultsItem.getOriginalTitle();
        this.video = resultsItem.isVideo();
        this.title = resultsItem.getTitle();
        this.posterPath = resultsItem.getPosterPath();
        this.backdropPath = resultsItem.getBackdropPath();
        this.releaseDate = resultsItem.getReleaseDate();
        this.voteAverage = resultsItem.getVoteAverage();
        this.popularity = resultsItem.getPopularity();
        this.adult = resultsItem.isVideo();
        this.voteCount = resultsItem.getVoteCount();
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(int voteAverage) {
        this.voteAverage = voteAverage;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(overview);
        parcel.writeString(originalLanguage);
        parcel.writeString(originalTitle);
        parcel.writeByte((byte) (video ? 1 : 0));
        parcel.writeString(title);
        parcel.writeString(posterPath);
        parcel.writeString(backdropPath);
        parcel.writeString(releaseDate);
        parcel.writeInt(voteAverage);
        parcel.writeDouble(popularity);
        parcel.writeInt(id);
        parcel.writeByte((byte) (adult ? 1 : 0));
        parcel.writeInt(voteCount);
        parcel.writeByte((byte) (favorite ? 1 : 0));
    }
}
