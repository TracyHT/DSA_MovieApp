/*  GROUP 17
    Ngo Le Thien An - ITITDK21030
    Huynh Thanh Thuy - ITITIU21325
    Cao Hoang Khoi Nguyen - ITITDK21048
    Nguyen Dinh Thang - ITITIU21309
    Purpose: This Java class represents an Android fragment for displaying a collection of movies categorized by genre, utilizing a ViewPager2 with TabLayout to switch between different genres and observe movie data changes accordingly.
*/
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
import androidx.viewpager2.widget.ViewPager2;
import com.example.myapplication.databinding.FragmentMoviesBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MoviesFragment extends Fragment {

    private FragmentMoviesBinding binding;
    private MoviesViewModel moviesViewModel;
    private MovieAdapter movieAdapter;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMoviesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize ViewModel
        moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

        // Initialize RecyclerView and Adapter
        movieAdapter = new MovieAdapter(requireContext()); // Passing context to the adapter
        RecyclerView recyclerViewMovies = binding.recyclerViewMovies;
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerViewMovies.setAdapter(movieAdapter);

        // Initialize ViewPager2 and TabLayout
        ViewPager2 viewPager2 = binding.viewPager;
        TabLayout tabLayout = binding.tabLayout;

        // Set up the ViewPager2 adapter
        viewPagerAdapter = new ViewPagerAdapter(requireActivity());
        viewPager2.setAdapter(viewPagerAdapter);

        // Connect TabLayout with ViewPager2 using TabLayoutMediator
        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText(getTabTitle(position))
        ).attach();

        // Observe changes in the movie list based on the selected tab
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                updateMovieList(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Do nothing
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Do nothing
            }
        });

        // Initially, display movies for the first tab
        updateMovieList(0);

        return root;
    }

    private String getTabTitle(int position) {
        // Customize tab titles based on your requirements
        switch (position) {
            case 0:
                return "Drama";
            case 1:
                return "Comedy";
            case 2:
                return "Action";
            case 3:
                return "Romance";
            default:
                return "Unknown";
        }
    }

    private void updateMovieList(int tabPosition) {
        // Update the movie list based on the selected tab
        switch (tabPosition) {
            case 0:
                moviesViewModel.getDramaMovies().observe(getViewLifecycleOwner(), movieAdapter::setMovies);
                break;
            case 1:
                moviesViewModel.getComedyMovies().observe(getViewLifecycleOwner(), movieAdapter::setMovies);
                break;
            case 2:
                moviesViewModel.getActionMovies().observe(getViewLifecycleOwner(), movieAdapter::setMovies);
                break;
            case 3:
                moviesViewModel.getRomanceMovies().observe(getViewLifecycleOwner(), movieAdapter::setMovies);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
