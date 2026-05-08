package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.databinding.Fragment3Binding;

public class Fragment_3 extends Fragment {

    private Fragment3Binding binding;
    private Fragment3Listener listener;

    private static final String ARG_NAME = "user_name";

    public interface Fragment3Listener {
        void onCheckboxStateChanged(boolean isChecked);
    }

    @NonNull
    public static Fragment_3 newInstance(String name) {
        Fragment_3 fragment = new Fragment_3();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Fragment3Listener) {
            listener = (Fragment3Listener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement Fragment3Listener");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = Fragment3Binding.inflate(inflater, container, false);

        if (getArguments() != null) {
            String name = getArguments().getString(ARG_NAME);
            binding.tvWelcome.setText("Welcome, " + name + "!");
        }

        binding.cbConfirm.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (listener != null) {
                listener.onCheckboxStateChanged(isChecked);
            }
        });

        return binding.getRoot();
    }

    public void updateDynamicButton(boolean isChecked) {
        binding.btnFinalAction.setEnabled(isChecked);
        binding.btnFinalAction.setText(isChecked ? "Finish" : "Continue");

        if (isChecked) {
            binding.btnFinalAction.setOnClickListener(v -> {
                // This will close the app
                requireActivity().finish();
            });
        } else {
            binding.btnFinalAction.setOnClickListener(null);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}