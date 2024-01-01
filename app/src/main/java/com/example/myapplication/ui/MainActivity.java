package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.ui.home.HomeActivity;
import com.example.myapplication.ui.home.HomeFragment;
import com.example.myapplication.ui.movies.MoviesFragment;
import com.example.myapplication.ui.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_home) {
                loadHomeFragment();
                return true;
            } else if (itemId == R.id.navigation_movies) {
                loadMoviesFragment();
                return true;
            } else if (itemId == R.id.navigation_ticket) {
                loadTicketFragment();
                return true;
            } else if (itemId == R.id.navigation_profile) {
                loadProfileFragment();
                return true;
            } else {
                return false;
            }
        });

    }


    private void loadHomeFragment() {
        // Implement the logic to load the HomeFragment here
        HomeFragment homeFragment = new HomeFragment();
        HomeActivity homeActivity = new HomeActivity();


        // Use a FragmentManager to handle the fragment transactions
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Replace the existing fragment (if any) with the ProfileFragment
        transaction.replace(R.id.fragment_container, homeFragment);

        // Commit the transaction
        transaction.commit();
        homeActivity.initView();
        homeActivity.banners();
        homeActivity.sendRequestBestMovies();
        homeActivity.sendRequestUpComming();
        homeActivity.sendRequestCategories();
    }

    private void loadTicketFragment() {
        // Implement the logic to load the TicketFragment here
    }

    private void loadProfileFragment() {
        ProfileFragment profileFragment = new ProfileFragment();

        // Use a FragmentManager to handle the fragment transactions
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Replace the existing fragment (if any) with the ProfileFragment
        transaction.replace(R.id.fragment_container, profileFragment);

        // Commit the transaction
        transaction.commit();
    }

    private void loadMoviesFragment() {
        MoviesFragment moviesFragment = new MoviesFragment();

        // Use a FragmentManager to handle the fragment transactions
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Replace the existing fragment (if any) with the MoviesFragment
        transaction.replace(R.id.fragment_container, moviesFragment);

        // Commit the transaction
        transaction.commit();
    }


}
