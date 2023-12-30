package com.example.myapplication.ui;

import android.os.Bundle;

import com.example.myapplication.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.ui.movies.MoviesFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load the MoviesFragment
        loadMoviesFragment();
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
