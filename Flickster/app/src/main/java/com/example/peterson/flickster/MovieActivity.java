package com.example.peterson.flickster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.peterson.flickster.adapters.MovieArrayAdapter;
import com.example.peterson.flickster.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class MovieActivity extends AppCompatActivity {

    ArrayList<Movie> movies;
    MovieArrayAdapter movieAdapter;
//    ListView lvItems;
    @BindView(R.id.lvMovies) ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);

//        lvItems = (ListView) findViewById(R.id.lvMovies);
        movies = new ArrayList<>();
        movieAdapter = new MovieArrayAdapter(this, movies);
        lvItems.setAdapter(movieAdapter);
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

        AsyncHttpClient client = new AsyncHttpClient();

         client.get(url, new JsonHttpResponseHandler(){
             @Override
             public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                 JSONArray movieJsonResults = null;
                 try {
                     movieJsonResults = response.getJSONArray("results");
                     movies.addAll(Movie.fromJSONArray(movieJsonResults));
                     movieAdapter.notifyDataSetChanged();
                     Log.d("DEBUG", movies.toString());
                 } catch (JSONException e) {
                     e.printStackTrace();
                 }
             }

             @Override
             public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                 super.onFailure(statusCode, headers, responseString, throwable);
             }
         });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = movies.get(position);
                showMoviesDetails(movie);
            }
        });
    }

    public void showMoviesDetails(Movie movie) {
        Intent activityDetails = new Intent(this, DetailsMovieActivity.class);
        activityDetails.putExtra("title", movie.getOriginalTitle());
        activityDetails.putExtra("overview", movie.getOverView());
        activityDetails.putExtra("img_url", movie.getBackDropPath());
        activityDetails.putExtra("vote_average", movie.getVoteAverage().toString());
        activityDetails.putExtra("has_video", movie.getHasVideo().toString());
        activityDetails.putExtra("date",movie.getReleaseDate());
        startActivityForResult(activityDetails, 1);
    }
}
