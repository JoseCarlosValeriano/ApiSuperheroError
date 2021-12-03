package com.example.apisuperhero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.apisuperhero.utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView urlDisplay;
    TextView searchResults;


    public class SuperHeroQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String toysURL = null;

            try {
                toysURL = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return toysURL;
        }

        @Override
        protected void onPostExecute(String s){
            if( s != null && !s.equals("")){
                searchResults.setText(s);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.launcher) {
            urlDisplay.setVisibility(View.VISIBLE);
            Context context = MainActivity.this;

            URL githubURL = NetworkUtils.buildUrl();
            urlDisplay.setText(githubURL.toString());

            new SuperHeroQueryTask().execute(githubURL);

        }

        if(itemId == R.id.clear){
            erraseScreen();
        }
        return true;
    }


    private void erraseScreen(){
        searchResults.setVisibility(View.INVISIBLE);
        urlDisplay.setVisibility(View.INVISIBLE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlDisplay = (TextView) findViewById(R.id.url_display);
        searchResults = (TextView) findViewById(R.id.github_search_result);

    }
}