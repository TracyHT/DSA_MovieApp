package com.example.myapplication.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentProfileBinding;
import com.example.myapplication.ui.login.LoginActivity;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.editInfo;
        profileViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Get reference to the logout button
        Button logoutButton = root.findViewById(R.id.button_logout);

        // Set click listener for the logout button
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method to handle logout
                logout();
            }
        });

        // Set click listener for the look up information button
        Button lookupButton = root.findViewById(R.id.lookup_info);

        // Set click listener for the look up information button
        lookupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method to move to ViewProfileActivity
                moveToViewProfileActivity();
            }
        });

        return root;
    }

    // Method to handle logout
    private void logout() {
        // Navigate to the login activity
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        // Clear the back stack so that the user cannot go back to the profile screen after logging out
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void moveToViewProfileActivity() {
        // Replace the current fragment with ViewProfileActivity
        Intent intent = new Intent(getActivity(), ViewProfileActivity.class);
        startActivity(intent);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}