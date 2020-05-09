package com.example.dapo.nnews;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {


    private ArrayList<NewsItem> newsItems;
    private Context context;

    public NewsAdapter(ArrayList<NewsItem> newsItems, Context context) {
        this.newsItems = newsItems;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_item, viewGroup, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = MainActivity.recyclerView.getChildLayoutPosition(v);
                String urlLink = MainActivity.newsItems.get(position).getNewsLink();
                Intent intent = new Intent(MainActivity.context, WebPageActivity.class);
                intent.putExtra("newsLink", urlLink);
                MainActivity.context.startActivity(intent);
            }
        });

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder viewHolder, int i) {


        NewsItem newsItem = newsItems.get(i);
        viewHolder.textView.setText(newsItem.getTitle());
        Picasso.get().load(newsItem.getImgUrl()).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.readTextView);
        }
    }
}
