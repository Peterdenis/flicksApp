package com.example.peterson.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Peterson on 7/7/2018.
 */

public class Movie {

    String posterPath;
    String originalTitle;
    String overView;
    String backDropPath;
    int movieID;
    Double voteAverage;
    Boolean hasVideo;
    Date releaseDate;


    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getBackDropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backDropPath);
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverView() {
        return overView;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public Boolean getHasVideo() {
        return hasVideo;
    }

    public int getMovieID() {
        return movieID;
    }



    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overView = jsonObject.getString("overview");
        this.backDropPath = jsonObject.getString("backdrop_path");
        this.voteAverage = jsonObject.getDouble("vote_average");
        this.hasVideo = jsonObject.getBoolean("video");
        this.movieID = jsonObject.getInt("id");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        try {
            this.releaseDate = dateFormat.parse(jsonObject.getString("release_date").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
//    public Movie(JSONObject jsonObject) throws JSONException {
//        this.voteAverage = jsonObject.getDouble("vote_average");
//    }

    public static ArrayList<Movie> fromJSONArray(JSONArray array) {
        ArrayList<Movie> results = new ArrayList<>();

        for (int x = 0; x < array.length(); x++) {
            try {
                results.add(new Movie(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}
