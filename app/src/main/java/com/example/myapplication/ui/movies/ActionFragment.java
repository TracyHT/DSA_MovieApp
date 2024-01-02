// ActionFragment.java
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

import com.example.myapplication.databinding.FragmentActionBinding;
import com.example.myapplication.ui.detailFilm.DetailActivity;

public class ActionFragment extends Fragment {

    private FragmentActionBinding binding;
    private MoviesViewModel moviesViewModel;
    private MovieAdapter movieAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentActionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize ViewModel
        moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

        // Initialize RecyclerView and Adapter
        movieAdapter = new MovieAdapter(requireContext());
        RecyclerView recyclerViewAction = binding.recyclerViewAction;
        recyclerViewAction.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewAction.setAdapter(movieAdapter);

        // Set the click listener in MovieAdapter
        movieAdapter.setOnMovieItemClickListener(this::startDetailActivity);

        // Observe changes in the action movies list
        moviesViewModel.findMoviesByGenre("Action").observe(getViewLifecycleOwner(), actionMovies -> {
            movieAdapter.setMovies(actionMovies);
        });

        return root;
    }

    private void startDetailActivity(int movieId) {
        // Handle the click event here, e.g., start a new activity with movie details
        // You can modify this part based on your DetailActivity implementation
        Intent intent = new Intent(requireActivity(), DetailActivity.class);
        intent.putExtra("id", movieId);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
