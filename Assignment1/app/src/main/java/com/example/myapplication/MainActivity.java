package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private int currentFragment = 1;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Fragment1 fragment1 = Fragment1.newInstance(null, null);
        Fragment2 fragment2 = Fragment2.newInstance(null, null);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment1)
                .commit();

        binding.buttonContinue.setOnClickListener(view -> {
            if (currentFragment == 1) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment2)
                        .addToBackStack(null)
                        .commit();
                currentFragment = 2;
            } else {
                 if (!TextUtils.isEmpty(
                         Fragment2.fragment2Binding.editText.getText().toString())) {
                     name = Fragment2.fragment2Binding.editText.getText().toString();
                     Toast.makeText(
                                     getApplicationContext(),
                                     name,
                                     Toast.LENGTH_SHORT)
                             .show();
                 } else {
                     Toast.makeText(
                             getApplicationContext(),
                             "Enter name!",
                             Toast.LENGTH_SHORT)
                             .show();
                 }
            }
        });
    }
}