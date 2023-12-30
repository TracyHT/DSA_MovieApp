package com.example.myapplication.ui.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RatingBar;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.myapplication.R; // Replace with the actual R class of your application
import com.example.myapplication.database.movies.MovieItem;
import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<MovieItem> movies = new ArrayList<>();

    public void setMovies(List<MovieItem> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieItem movie = movies.get(position);

        // Set movie details to the views
        holder.textViewTitle.setText(movie.getTitle());
        holder.textViewGenre.setText(movie.getGenre());

        // Load movie poster using Glide library
        Glide.with(holder.itemView.getContext())
                .load(movie.getImageUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageViewPoster);

        // Set movie rating
        holder.ratingBar.setRating(movie.getRating()); // Replace with the actual method to get movie rating
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPoster;
        TextView textViewTitle;
        TextView textViewGenre;
        RatingBar ratingBar;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.imageViewMoviePoster);
            textViewTitle = itemView.findViewById(R.id.textViewMovieTitle);
            textViewGenre = itemView.findViewById(R.id.textViewMovieGenre);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            // Initialize other views as needed
        }
    }
}
