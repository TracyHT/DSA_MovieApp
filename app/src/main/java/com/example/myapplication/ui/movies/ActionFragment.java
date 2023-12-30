// DramaFragment.java
package com.example.myapplication.ui.movies;

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
        movieAdapter = new MovieAdapter();
        RecyclerView recyclerViewAction = binding.recyclerViewAction;
        recyclerViewAction.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewAction.setAdapter(movieAdapter);

        // Observe changes in the drama movies list
        moviesViewModel.findMoviesByGenre("Action").observe(getViewLifecycleOwner(), dramaMovies -> {
            movieAdapter.setMovies(dramaMovies);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
