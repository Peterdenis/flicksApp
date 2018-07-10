package com.example.peterson.flickster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MovieTrailer extends YouTubeBaseActivity {

    private static final String youtube_key_api = "AIzaSyBQfgmGrMeVz3a5q-Nk3YdSMTbhn6b5fz8";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_trailer);

        final String key = getIntent().getStringExtra("key_video");
        final String play = getIntent().getStringExtra("key_video");
        final int id = getIntent().getIntExtra("movie_ID", 0);


        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.player);

        youTubePlayerView.initialize(youtube_key_api, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if (provider.equals("key_video")) {
                    youTubePlayer.loadVideo(key);
                }
//                else {
//                    youTubePlayer.loadVideo(play);
//                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }
}
