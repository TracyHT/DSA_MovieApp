package com.example.myapplication.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.database.movies.MovieItem;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final List<MovieItem> movies;
    private final Context context;

    public MovieAdapter(List<MovieItem> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieItem movie = movies.get(position);

        // Set movie name
        holder.movieName.setText(movie.getTitle());

        // Load movie poster using Glide
        Glide.with(context)
                .load(movie.getImageUrl())
                .into(holder.moviePoster);
    }

    public void onBindViewHolderCategories(@NonNull MovieViewHolder holder, int position) {
        MovieItem movie = movies.get(position);

        // Set movie name
        holder.movieName.setText(movie.getGenre());

        // Load movie poster using Glide
        Glide.with(context)
                .load(movie.getImageUrl())
                .into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView moviePoster;
        TextView movieName;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            moviePoster = itemView.findViewById(R.id.moviePoster);
            movieName = itemView.findViewById(R.id.movieName);
        }
    }
}
