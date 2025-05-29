package com.example.futurefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class MajorResultsScreen extends AppCompatActivity {

    ArrayList<String> compFields = new ArrayList<String>();
    ArrayList<String> words = new ArrayList<String>();
    LinearLayout resultView;
    String parentClass = "MajorResultsScreen";

    //sharedPreference to save the compatible majors when coming back from CollegeResultsScreen
    //private SharedPreferences compFieldsSharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_results_screen);
        TextView startText = (TextView) findViewById(R.id.textView3);
        resultView = findViewById(R.id.resultVIEW);

        //gets array of Strings with compatible fields of study
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            compFields = getIntent().getExtras().getStringArrayList("fieldOfStudyList");
        }
        readIn();
        Button findACollegeButton = findViewById(R.id.findCollegeWithMajor);
        findACollegeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userCode = getCollegeCode();
                Bundle extras = new Bundle();
                extras.putString("userCode", userCode);
                extras.putString("parentClass", parentClass);
                Intent intentNext = new Intent(MajorResultsScreen.this , CollegeResultsScreen.class);
                intentNext.putExtras(extras);
                startActivity(intentNext);
            }
        });

        Drawable background = getDrawable(R.drawable.rounded_button);
        for(int i = 0 ; i<compFields.size();i++)
        {
            for(int j = 0; j< words.size();j++)
            {
                if(compFields.get(i).equals(words.get(j)))
                {
                    for(int k = j+1; k < j+10;k++)
                    {
                        final Button majorButton = new Button(this);
                        majorButton.setText(words.get(k));
                        majorButton.setTag(words.get(k));
                        majorButton.setBackground(background);
                        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        lParams.setMargins(0, 0, 0, 35);
                        majorButton.setTextColor(Color.parseColor("#FFFFFF"));
                        majorButton.setLayoutParams(lParams);
                        majorButton.setOnClickListener(new View.OnClickListener(){
                            public void onClick(View v){
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_VIEW);
                                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                                try{
                                    intent.setData(Uri.parse(getMajorLink(majorButton)));
                                }catch(IOException e){
                                    throw new RuntimeException(e);
                                }

                                startActivity(intent);
                            }
                        });
                        resultView.addView(majorButton);
                    }
                }
            }
        }

        //sets heading of majorResultsScreen
        String temp = "Your compatible majors for ";
        for (String fields : compFields) {
            temp += fields + ", ";
        }
        temp += " are: ";
        startText.setText(temp);

    }

    public void onFindAJobClick(View v){
        String jobSearch = compFields.get(0);
        Bundle extras = new Bundle();
        extras.putString("jobSearch", jobSearch);
        extras.putString("stateFilt", "");
        extras.putString("jobTypeFilt", "");
        Intent intentNext = new Intent(MajorResultsScreen.this , JobResultsScreen.class);
        intentNext.putExtras(extras);
        startActivity(intentNext);
    }

    //gets corresponding link for major from txt file, to be searched in google
    public String getMajorLink(Button myButton)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("majorLinks.txt"), StandardCharsets.UTF_8));
        String majorLink = "";
        String major = "";

        while(br.ready()){
            major = br.readLine();
            if(major.equals(myButton.getTag())){
                majorLink = br.readLine();
            }
        }
        return majorLink;
    }

    //gets code for college to send to College results screen
    public String getCollegeCode(){
        String code = "";
        for(int i = 0; i<compFields.size();i++)
        {
            if(compFields.get(i).equals("STEM"))
            {
                code = code + "a";
            }
            if(compFields.get(i).equals("SocialScience"))
            {
                code = code + "b";
            }
            if(compFields.get(i).equals("Business"))
            {
                code = code + "c";
            }
            if(compFields.get(i).equals("Health"))
            {
                code = code + "d";
            }
            if(compFields.get(i).equals("Art"))
            {
                code = code + "e";
            }
        }
        return code;
    }

    public void readIn()
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("Majors.txt"), StandardCharsets.UTF_8));
            String line = "";
            int i = 0;
            line = br.readLine();
            while(br.ready())
            {
                words.add(line);
                i++;
                line = br.readLine();
            }

        }
        catch (IOException e) {
            System.out.println("not working" + e);
        }
    }
}