package com.example.peterson.flickster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.peterson.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

import static java.security.AccessController.getContext;

public class DetailsMovieActivity extends AppCompatActivity {

    Movie movie;
//    TextView txtTitle;
//    TextView txtOverview;
//    TextView txtRating;
//    RatingBar brtRating;
//    ImageView ivImage;
//    ImageButton imagePlay;
    @BindView(R.id.tvDetailsTitle) TextView txtTitle;
    @BindView(R.id.tvDetailsOverview) TextView txtOverview;
    @BindView(R.id.textView) TextView txtRating;
    @BindView(R.id.tvRating) RatingBar brtRating;
    @BindView(R.id.imageView2) ImageView ivImage;
    @BindView(R.id.imBtnMovie) ImageButton imagePlay;
    @BindView(R.id.DateRelease) TextView dateRelease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);
        ButterKnife.bind(this);

//        txtTitle = (TextView) findViewById(R.id.tvDetailsTitle);

//        txtOverview = (TextView) findViewById(R.id.tvDetailsOverview);
//        txtRating = (TextView) findViewById(R.id.textView);
//        brtRating = (RatingBar) findViewById(R.id.tvRating);
//        ivImage = (ImageView) findViewById(R.id.imageView2);
//        imagePlay = (ImageButton) findViewById(R.id.imBtnMovie);



        // receive the data from the first activity
        String receiveTitle = getIntent().getStringExtra("title");
        String receiveOverView = getIntent().getStringExtra("overview");
        String receiveAverage = getIntent().getStringExtra("vote_average");
        String receiveImageUrl = getIntent().getStringExtra("img_url");
        String receiveDate = getIntent().getStringExtra("date");

        imagePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailsMovieActivity.this, MovieTrailer.class);
                i.putExtra("key_video", movie.getHasVideo());
                i.putExtra("movie_ID", movie.getMovieID());
                startActivity(i);
            }
        });



        Picasso.with(this).load(receiveImageUrl)
                .transform(new RoundedCornersTransformation(5, 5))
                .into(ivImage, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });

        txtTitle.setText(receiveTitle);
        txtOverview.setText(receiveOverView);
        dateRelease.setText(receiveDate);
        brtRating.setRating(Float.parseFloat(receiveAverage));
        txtRating.setText("Rating: " +receiveAverage);

    }
}
