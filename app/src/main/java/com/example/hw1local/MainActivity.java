package com.example.hw1local;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        if(savedInstanceState == null){
            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.recycler_fragment, new RecyclerFragment()).commit();
        }
    }
}