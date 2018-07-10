package com.example.peterson.flickster.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peterson.flickster.MovieTrailer;
import com.example.peterson.flickster.R;
import com.example.peterson.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by Peterson on 7/7/2018.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {


    // View lookup cache
    private static class ViewHolder {
        TextView title;
        TextView overview;
        ImageView imageview;
        ImageView imageButton;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }


    // for selected popular movie
//    public int getItemViewType(int pos) {
//        if(movie.getVoteAverage() <= 5.0f) {
//            return 0;
//        }else {
//            return  1;
//        }
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get the data item for position
        final Movie movie = getItem(position);
        // check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        int orientation = getContext().getResources().getConfiguration().orientation;
        viewHolder = new ViewHolder();
        Double average = movie.getVoteAverage();
//        int itemViewType = getItemViewType(position);
        // check the existing view being reused
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            LayoutInflater inflater = LayoutInflater.from(getContext());

//            if (itemViewType == 0) {
                convertView = inflater.inflate(R.layout.item_movie, parent, false);
//            } else if(itemViewType == 1 && orientation == Configuration.ORIENTATION_PORTRAIT) {
//                convertView = inflater.inflate(R.layout.item_full_image, parent, false);
//            }else{
//                convertView = inflater.inflate(R.layout.item_movie, parent, false);
//            }

            viewHolder.title = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.overview = (TextView) convertView.findViewById(R.id.tvOverView);
            viewHolder.imageview = (ImageView) convertView.findViewById(R.id.ivMovieImage);
            viewHolder.imageButton = (ImageView) convertView.findViewById(R.id.ibMovieTrailer);


            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewholder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // find the image view
        viewHolder.imageview.setImageResource(0);
        String imagePath = movie.getPosterPath();
//        if (movie.getVoteAverage() <= 5.0) {
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Picasso.with(getContext()).load(movie.getBackDropPath())
                        .placeholder(R.drawable.movie_placeholder)
                        .error(R.drawable.movie_placeholder)
                        .transform(new RoundedCornersTransformation(10,10))
                        .into(viewHolder.imageview);
            }else {
                Picasso.with(getContext()).load(movie.getPosterPath())
                        .placeholder(R.drawable.movie_placeholder)
                        .error(R.drawable.movie_placeholder)
                        .transform(new RoundedCornersTransformation(10, 10))
                        .into(viewHolder.imageview);
            }
        // Test for popular movie to show button
        if(average <= 5.0) {
            viewHolder.imageButton.setVisibility(View.GONE);
       }
       viewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(getContext(), MovieTrailer.class);
               i.putExtra("key_video",movie.getHasVideo());
               i.putExtra("movieID", movie.getMovieID());
               getContext().startActivity(i);
           }
       });


//        }
//        }else {
        // populate data
        viewHolder.title.setText(movie.getOriginalTitle());
        viewHolder.overview.setText(movie.getOverView());

            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // populate data
                viewHolder.title.setText(movie.getOriginalTitle());
                viewHolder.overview.setText(movie.getOverView());
                Picasso.with(getContext()).load(movie.getBackDropPath())
                        .placeholder(R.drawable.movie_placeholder)
                        .error(R.drawable.movie_placeholder)
                        .transform(new RoundedCornersTransformation(10,10))
                        .into(viewHolder.imageview);
            }else {
                viewHolder.title.setText(movie.getOriginalTitle());
                viewHolder.overview.setText(movie.getOverView());
                Picasso.with(getContext()).load(movie.getPosterPath())
                        .placeholder(R.drawable.movie_placeholder)
                        .error(R.drawable.movie_placeholder)
                        .transform(new RoundedCornersTransformation(10,10))
                        .into(viewHolder.imageview);
            }
//        }
        // return the view
        return convertView;
    }




}
