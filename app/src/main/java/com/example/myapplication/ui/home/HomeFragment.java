package com.example.myapplication.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.example.myapplication.Slider.Adapter.SliderAdapter;
import com.example.myapplication.Slider.Domain.SliderItems;
import com.example.myapplication.database.movies.MovieDatabase;
import com.example.myapplication.database.movies.MovieItem;
import com.example.myapplication.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ViewPager2 viewPager2;
    private Handler slideHandler = new Handler();
    private MovieDatabase movieDatabase;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager2 = binding.viewpageSlider;

        // Initialize Movie Database
        movieDatabase = new MovieDatabase();

        // Initialize your views and perform necessary logic
        initView();

        // Implement your banner logic
        banners();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initView() {
        // Set up RecyclerViews
        RecyclerView recyclerViewBestMovies = binding.view1;
        RecyclerView recyclerViewUpcoming = binding.view2;
        RecyclerView recyclerViewCategories = binding.view3;

        // Set up LayoutManagers
        LinearLayoutManager layoutManagerBestMovies = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManagerUpcoming = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManagerCategories = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerViewBestMovies.setLayoutManager(layoutManagerBestMovies);
        recyclerViewUpcoming.setLayoutManager(layoutManagerUpcoming);
        recyclerViewCategories.setLayoutManager(layoutManagerCategories);

        // Add your adapters and populate data
        List<MovieItem> bestMovies = movieDatabase.findMoviesByAttribute("genre", "Action");
        List<MovieItem> upcomingMovies = movieDatabase.findMoviesByAttribute("genre", "Upcoming");
        List<MovieItem> categoryMovies = movieDatabase.findMoviesByAttribute("genre", "Romance");

        MovieAdapter bestMoviesAdapter = new MovieAdapter(bestMovies, requireContext());
        MovieAdapter upcomingMoviesAdapter = new MovieAdapter(upcomingMovies, requireContext());
        MovieAdapter categoryMoviesAdapter = new MovieAdapter(categoryMovies, requireContext());

        recyclerViewBestMovies.setAdapter(bestMoviesAdapter);
        recyclerViewUpcoming.setAdapter(upcomingMoviesAdapter);
        recyclerViewCategories.setAdapter(categoryMoviesAdapter);
    }

    private void banners() {
        List<MovieItem> allMovies = movieDatabase.getAllMovies();

        List<SliderItems> sliderItems = new ArrayList<>();

        // Add movies to the slider
        for (MovieItem movie : allMovies) {
            sliderItems.add(new SliderItems(movie.getImageUrl()));
        }

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_ALWAYS);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * 0.15f);
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.setCurrentItem(1);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(slideR);
            }
        });
    }
    private Runnable slideR = () -> viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);

    @Override
    public void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(slideR);
    }

    @Override
    public void onResume() {
        super.onResume();
        slideHandler.postDelayed(slideR, 2000);
    }

    public void onDataLoaded() {

    }
}
