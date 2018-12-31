package com.example.android.popularmovies.utilities;


import android.net.Uri;
import android.util.Log;
import com.example.android.popularmovies.APIKey;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the movie servers.
 */
public final class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String THE_MOVIE_DB_REQUEST_URL =
            "https://api.themoviedb.org/3/discover/movie?";
    final static String API_KEY_LABEL_NAME = "api_key";
    final static String LANGUAGE = "language";
    final static String ENGLISH_LANGUAGE = "en-US";
    final static String SORT_BY = "sort_by";
    final static String PAGE_NUMBER = "page";

    /**
     * Builds the URL used to talk to the movie server.
     *
     * @return The URL to use to query the movie server.
     */
    public static URL buildUrl(String sortOrderParam, String pageNumber) {
        Uri builtUri = Uri.parse(THE_MOVIE_DB_REQUEST_URL).buildUpon()
                .appendQueryParameter(API_KEY_LABEL_NAME, APIKey.API_KEY)
                .appendQueryParameter(LANGUAGE, ENGLISH_LANGUAGE)
                .appendQueryParameter(SORT_BY,sortOrderParam)
                .appendQueryParameter( PAGE_NUMBER, pageNumber)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}