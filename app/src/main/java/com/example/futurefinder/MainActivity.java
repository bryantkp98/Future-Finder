package com.example.futurefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void findACollege(View view){
        Intent intent = new Intent(MainActivity.this , CollegeSelectionScreen.class);
        startActivity(intent);
    }

    public void findAMajor(View view){
        Intent intent = new Intent(MainActivity.this , MajorSelectionScreen.class);
        startActivity(intent);
    }

    public void findAJob(View view){
        Intent intent = new Intent(MainActivity.this , JobSearchScreen.class);
        startActivity(intent);
    }
    public void Settings(View view){
        Intent intent = new Intent(MainActivity.this , SettingsActivity.class);
        startActivity(intent);
    }
}
