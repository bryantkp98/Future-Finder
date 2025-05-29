package com.example.futurefinder;

import androidx.appcompat.app.AppCompatActivity;

//import android.content.Context;
import android.content.Intent;
//import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
//import com.google.gson.Gson;
import android.net.Uri;
import android.os.Bundle;
//import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

//import java.io.IOException;
import java.util.ArrayList;

public class JobResultsScreen extends AppCompatActivity {
    public static ArrayList<Job> jobArrayList = new ArrayList<Job>();
    LinearLayout resultsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_results_screen);

        Bundle bundle = getIntent().getExtras();

        String searchVal = bundle.getString("jobSearch");
        String searchJobState = bundle.getString("stateFilt");
        String searchJobType = bundle.getString("jobTypeFilt");

        searchScraper(searchVal, searchJobState, searchJobType);

        resultsView = findViewById(R.id.resultsList);

    }

    public void populateResults(){
        for(Job job: jobArrayList) {
            Button jobButton = new Button(this);
            jobButton.setText(job.getTitle() + "\n" + "\n" + job.getCompany());
            Drawable background = getDrawable(R.drawable.rounded_button);
            jobButton.setBackground(background);
            LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            lParams.setMargins(0, 0, 0, 35);
            jobButton.setTextColor(Color.parseColor("#FFFFFF"));
            jobButton.setLayoutParams(lParams);

            final String url = job.getUrl();
            jobButton.setTag(url);
            jobButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    try {
                        intent.setData(Uri.parse(url));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    startActivity(intent);
                }
            });
            resultsView.addView(jobButton);
            jobArrayList = new ArrayList<Job>();
        }
    }

//    public void saveArrayList(ArrayList<String> list, String key){
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor editor = prefs.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(list);
//        editor.putString(key, json);
//        editor.apply();     // This line is IMPORTANT !!!
//    }

    public void searchScraper(String jobVal, String jobState, String jobType){
        SimplyHiredScraper scraper = new SimplyHiredScraper() {
            @Override
            protected void onPostExecute(ArrayList<Job> jobs) {
                try{
                    jobArrayList.addAll(jobs);
                    populateResults();
                }catch(Exception e){
                    System.out.println("Results for scraper not working");
                }
            }
        };
        scraper.execute(jobVal, jobState, jobType);
    }
}
