package com.example.myapplication.ui.profile;

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
import com.example.myapplication.database.user.User;
import com.example.myapplication.ui.login.LoginActivity;

public class UserProfileFragment extends Fragment {

    private UserProfileViewModel userProfileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userProfileViewModel =
                new ViewModelProvider(this).get(UserProfileViewModel.class);

        View root = inflater.inflate(R.layout.fragment_userprofile, container, false);

        final TextView usernameTextView = root.findViewById(R.id.UsernameTxt);
        final TextView emailTextView = root.findViewById(R.id.EmailTxt);
        final TextView phoneTextView = root.findViewById(R.id.PhoneTxt);
        final TextView passwordTextView = root.findViewById(R.id.PasswordTxt);

        // Get the logged-in user
        LoginActivity loginActivity = (LoginActivity) getActivity();
        if (loginActivity != null) {
            User loggedInUser = loginActivity.getLoggedInUser();
            if (loggedInUser != null) {
                // Set user information to TextViews
                usernameTextView.setText(loggedInUser.getUsername());
                emailTextView.setText(loggedInUser.getEmail());
                phoneTextView.setText(loggedInUser.getPhone());
                passwordTextView.setText(loggedInUser.getPassword()); // You may want to handle password securely
            }
        }

        // Get reference to the back button
        Button backButton = root.findViewById(R.id.backButton);

        // Set click listener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method to go back to the ProfileFragment
                goBackToProfile();
            }
        });

        return root;
    }


    // Method to handle going back to the ProfileFragment
    private void goBackToProfile() {
        // You can use FragmentManager to navigate back to the ProfileFragment
        // For example, if you are using getSupportFragmentManager:
        requireActivity().getSupportFragmentManager().popBackStack();
    }
}
