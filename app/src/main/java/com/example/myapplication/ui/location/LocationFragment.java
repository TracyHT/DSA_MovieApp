package com.example.myapplication.ui.location;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentLocationBinding;

public class LocationFragment extends Fragment {

    private FragmentLocationBinding binding;
    private LocationAdapter locationAdapter;
    private LocationViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Set up the TextView
        binding.startLocationTextView.setText(getString(R.string.location_near_you));

        // Set up the Spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                getSpinnerData()
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.movieTheaterSpinner.setAdapter(spinnerAdapter);

        // Set up the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        locationAdapter = new LocationAdapter(requireContext(), getViewModel(), getViewLifecycleOwner());
        binding.cinemaListRecyclerView.setAdapter(locationAdapter);
        binding.cinemaListRecyclerView.setLayoutManager(layoutManager);

        // Set up item selection listener for the Spinner
        binding.movieTheaterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Get the selected item from the Spinner
                String selectedTheater = (String) parentView.getItemAtPosition(position);

                // Update the list of locations based on the selected theater
                viewModel.setSelectedCinema(selectedTheater);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing when nothing is selected
            }
        });

        return view;
    }

    private String[] getSpinnerData() {
        // Replace this method with your logic to retrieve Spinner data
        return new String[]{"Cinema A", "Cinema B", "Cinema C", "Cinema D"};
    }

    private LocationViewModel getViewModel() {
        if (viewModel == null) {
            viewModel = new ViewModelProvider(this).get(LocationViewModel.class);
        }
        return viewModel;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
