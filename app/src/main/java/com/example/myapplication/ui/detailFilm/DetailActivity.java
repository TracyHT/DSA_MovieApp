package com.example.myapplication.ui.detailFilm;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.database.movies.MovieDatabase;
import com.example.myapplication.database.movies.MovieItem;
import com.example.myapplication.ui.adapters.ActorsListAdapter;

public class DetailActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView titleText, movieRateTxt, movieTimeTxt, movieSummaryInfo;
    private int idFilm;
    private ImageView pic2, backImg;
    private RecyclerView.Adapter adapterActorList;
    private RecyclerView recyclerViewActors;
    private NestedScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_film);

        idFilm = getIntent().getIntExtra("id", 0);
        initView();
        bindData();
    }

    private void bindData() {
        MovieDatabase movieDatabase = new MovieDatabase();
        MovieItem movieItem = movieDatabase.findMovieById(idFilm);

<<<<<<< Updated upstream
        mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/" + idFilm, response ->  {
            Gson gson = new Gson();
            progressBar.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);

            FilmItem item = gson.fromJson(response, FilmItem.class);

            Glide.with(DetailActivity.this).load(item.getPoster()).into(pic2);

            titleText.setText(item.getTitle());
            movieRateTxt.setText(item.getImdbRating());
            movieTimeTxt.setText(item.getRuntime());
            movieSummaryInfo.setText(item.getPlot());
            movieActorsInfo.setText(item.getActors());
            if (item.getImages() != null) {
                adapterActorList = new ActorsListAdapter(item.getImages());
                recyclerViewActors.setAdapter(adapterActorList);
            }
//                if (item.getGenres()) != null) {
//                    adapterCategory = new CategoryEachFilmListAdapter(item.getGenres());
//                    recyclerViewCategory.setAdapter(adapterCategory);
//                }
        }, error -> progressBar.setVisibility(View.GONE));
        mRequestQueue.add(mStringRequest);
=======
        if (movieItem != null) {
            progressBar.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);

            Glide.with(this).load(movieItem.getImageUrl()).into(pic2);

            titleText.setText(movieItem.getTitle());
            movieRateTxt.setText(String.valueOf(movieItem.getRating()));
            movieTimeTxt.setText(String.valueOf(movieItem.getDurationMinutes()));
            movieSummaryInfo.setText(movieItem.getSynopsis());

            // Assuming you have an 'Images' class within MovieItem
            if (movieItem.getImages() != null) {
                adapterActorList = new ActorsListAdapter(movieItem.getImages());
                recyclerViewActors.setAdapter(adapterActorList);
            }
        } else {
            // Handle the case where the movie with the specified id is not found
            progressBar.setVisibility(View.GONE);
        }
>>>>>>> Stashed changes
    }

    private void initView() {
        titleText = findViewById(R.id.movieNameTxt);
        progressBar = findViewById(R.id.progressBarDetail);
        scrollView = findViewById(R.id.scrollView2);
        pic2 = findViewById(R.id.picDetail);
        movieRateTxt = findViewById(R.id.movieStar);
        movieTimeTxt = findViewById(R.id.movieTime);
<<<<<<< Updated upstream
        movieSummaryInfo = findViewById(R.id.summaryText);
        movieActorsInfo = findViewById(R.id.movieActorText);
=======
        movieSummaryInfo = findViewById(R.id.movieSummary);
>>>>>>> Stashed changes
        backImg = findViewById(R.id.backImg);
        recyclerViewActors = findViewById(R.id.imageRecycler);
        recyclerViewActors.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        backImg.setOnClickListener(v -> finish());
    }
}