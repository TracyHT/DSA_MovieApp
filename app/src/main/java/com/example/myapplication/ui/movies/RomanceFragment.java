// RomanceFragment.java
package com.example.myapplication.ui.movies;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.FragmentRomanceBinding;
import com.example.myapplication.ui.detailFilm.DetailActivity;

public class RomanceFragment extends Fragment implements MovieAdapter.OnMovieItemClickListener {
    private FragmentRomanceBinding binding;
    private MoviesViewModel moviesViewModel;
    private MovieAdapter movieAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRomanceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize ViewModel
        moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

        // Initialize RecyclerView and Adapter
        movieAdapter = new MovieAdapter(requireContext());
        RecyclerView recyclerViewMovies = binding.recyclerViewRomance;
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewMovies.setAdapter(movieAdapter);

        // Set the click listener in MovieAdapter
        movieAdapter.setOnMovieItemClickListener(this);

        // Observe changes in the romance movies list
        moviesViewModel.findMoviesByGenre("Romance").observe(getViewLifecycleOwner(), romanceMovies -> {
            movieAdapter.setMovies(romanceMovies);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onMovieItemClick(int movieId) {
        // Handle the click event here, e.g., start a new activity with movie details
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("id", movieId);
        startActivity(intent);
    }
}
