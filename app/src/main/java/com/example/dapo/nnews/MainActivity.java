package com.example.dapo.nnews;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   static RecyclerView recyclerView;
    NewsAdapter adapter;
    static ArrayList<NewsItem> newsItems;
    ProgressBar progressBar;


    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        newsItems = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewsAdapter(newsItems, this);
        recyclerView.setAdapter(adapter);

        new NewsLoader().execute("http://newsapi.org/v2/top-headlines?country=ng&apiKey=bf49dcf6806143d4a43b12379d3fcc36");


    }


    public class NewsLoader extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);

            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_out));
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled(String aVoid) {
            super.onCancelled(aVoid);
        }

        @Override
        protected String doInBackground(String... urls) {


            try {

                URL url;
                HttpURLConnection connection;

                String result = "";

                url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;

                while((line = reader.readLine()) != null) {

                    result += line;


                }


                JSONObject jsonObject = new JSONObject(result);


                JSONArray jsonArray = jsonObject.getJSONArray("articles");

                for(int i = 0; i < jsonArray.length(); i++) {

                    Log.i("doInBackground", String.valueOf(i));

                    String title = jsonArray.getJSONObject(i).getString("title");

                    String imgUrl = jsonArray.getJSONObject(i).getString("urlToImage");

                    String newsLink = jsonArray.getJSONObject(i).getString("url");

                    Log.i("doInBackground", title);

                    Log.i("doInBackground", imgUrl);

                    newsItems.add(new NewsItem(imgUrl, title, newsLink));


                }


            } catch (Exception e) {

                e.printStackTrace();

            }


            return "Error";
        }
    }
}
