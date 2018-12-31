package com.example.android.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    String[] receivedDataStringArray ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        ImageView mMovieThumbnail = findViewById(R.id.details_movie_thumbnail);

        TextView mMovieTitle = findViewById(R.id.details_movie_title);
        TextView mMovieSynopsis = findViewById(R.id.details_synopsis);
        TextView mMovieRating = findViewById(R.id.details_rating);
        TextView mMovieReleaseDate = findViewById(R.id.details_date);
        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
                receivedDataStringArray = intentThatStartedThisActivity.getStringArrayExtra(Intent.EXTRA_TEXT);

                Picasso.get()
                        .load(receivedDataStringArray[1])
                        .error(R.drawable.noposter)
                        .into( mMovieThumbnail);
                mMovieTitle.setText(receivedDataStringArray[2]);
                mMovieSynopsis.setText(receivedDataStringArray[3]);
                mMovieRating.setText(receivedDataStringArray[4]);
                mMovieReleaseDate.setText(receivedDataStringArray[5]);
            }
        }
        else mMovieTitle.setText(getString(R.string.error));

    }
}


