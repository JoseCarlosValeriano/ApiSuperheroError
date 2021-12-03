package com.example.apisuperhero.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    final static String API_SUPERHERO = "https://akabab.github.io/superhero-api/api/all.json";

    public static URL buildUrl() {
        Uri buildUri = Uri.parse(API_SUPERHERO).buildUpon()
                .build();

        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }


    public static String getResponseFromHttpUrl(URL searchUrl) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) searchUrl.openConnection();

        InputStream in = urlConnection.getInputStream();

        Scanner scanner = new Scanner(in);
        scanner.useDelimiter("\\A");

        try {
            boolean hasInoput = scanner.hasNext();

            if (hasInoput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}

