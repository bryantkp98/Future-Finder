package com.example.futurefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MajorSelectionScreen extends AppCompatActivity {

    LinearLayout viewLinLay;

    FieldOfStudy STEM = new FieldOfStudy("STEM", 0);
    FieldOfStudy socSci = new FieldOfStudy("Social Science", 0);
    FieldOfStudy business = new FieldOfStudy("Business", 0);
    FieldOfStudy health = new FieldOfStudy("Health", 0);
    FieldOfStudy art = new FieldOfStudy("Art", 0);

    ArrayList<RadioButton> rbList = new ArrayList<RadioButton>();
    ArrayList<FieldOfStudy> fosList = new ArrayList<FieldOfStudy>();
    ArrayList<String> compFOSList = new ArrayList<String>();

    int radioGroupNum;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_selection_screen);
        viewLinLay = findViewById(R.id.linLay);

        try {
            generateQuestions();
        } catch(IOException e){
            throw new RuntimeException(e);
        }

    }

    public void backButton(View view){
        Intent goBack = new Intent(MajorSelectionScreen.this , MainActivity.class);
        startActivity(goBack);
    }

    //checks if all radioButtons are clicked before submitting, calls a toast if not
    public void submitButton(View view){

        if (allChecked()) {
            getCompatibleField();
            Intent intentNext = new Intent(MajorSelectionScreen.this , MajorResultsScreen.class);
            intentNext.putExtra("fieldOfStudyList", compFOSList);
            startActivity(intentNext);
        }
        else{
            Toast t = Toast.makeText(this, "Must answer all before submitting", Toast.LENGTH_LONG);
            t.show();
        }
    }

    //goes through every radioButton and checks if all radioButtons have been checked ie.) total == 25
    public boolean allChecked(){
        boolean allChecked = false;
        int total = 0;
        for(RadioButton rb: rbList){
            if(rb.isChecked()){
                total++;
            }
        }

        if (total == 25){
            allChecked = true;
        }

        return allChecked;
    }


    public void generateQuestions() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("questions.txt"), StandardCharsets.UTF_8));
        radioGroupNum = 1;
        while(br.ready()){

            //creates textView with corresponding information
            TextView tv = new TextView(this);
            String strng = br.readLine();
            tv.setText(strng);
            viewLinLay.addView(tv);

            //create a text view that indicates strongly disagree-------strongly agree

            //creates radioGroup with horizontal layout
            RadioGroup rg = new RadioGroup(this);
            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedID) {
                    onRadioButtonClicked(group, checkedID);
                }
            });

            //assigns radioGroup with correct tag
            if(radioGroupNum <= 5){
                rg.setTag("STEM");
            }
            else if(radioGroupNum <= 10){
                rg.setTag("SocialScience");
            }
            else if(radioGroupNum <= 15){
                rg.setTag("Business");
            }
            else if(radioGroupNum <= 20){
                rg.setTag("Health");
            }
            else if(radioGroupNum <= 25){
                rg.setTag("Art");
            }
            rg.setId(radioGroupNum);
            rg.setOrientation(LinearLayout.HORIZONTAL);
            viewLinLay.addView(rg);

            //creates 5 radioButtons per radioGroup and onClickListener for each radioButton
            int numButtons = 5;
            for(int i = 0; i < numButtons; i++) {
                RadioButton rdbtn = new RadioButton(this);
                rg.addView(rdbtn);
                rbList.add(rdbtn);
            }
            radioGroupNum++;
        }

        //assigns radioButtons of ID 1-125,m and tag "rb**1-125**"
        for(int i = 0; i <= 124; i++){
            int id = i+1;
            rbList.get(i).setId(id);
            String tag = "rb" + (i+1);
            rbList.get(i).setTag(tag);
        }
    }



    //method that is called when a radioButton is clicked
    // big problem was getting the right number to be added to the total
    public void onRadioButtonClicked(RadioGroup group, int checkedID){
        int one = 1;
        if(group.getTag().equals("STEM")){
            STEM.setTotal(STEM.getTotal() + (checkedID - ((group.getId() - one) * 5)));
            System.out.println("wowSTEM" + STEM.getTotal());
        }
        else if(group.getTag().equals("SocialScience")){
            socSci.setTotal(socSci.getTotal() + (checkedID - ((group.getId() - one) * 5)));
            System.out.println("wowSocial" + socSci.getTotal());
        }

        else if(group.getTag().equals("Business")){
            business.setTotal(business.getTotal() + (checkedID - ((group.getId() - one) * 5)));
            System.out.println("wowBusiness" + business.getTotal());
        }
        else if(group.getTag().equals("Health")){
            health.setTotal(health.getTotal() + (checkedID - ((group.getId() - one) * 5)));
            System.out.println("wowHealth" + health.getTotal());
        }
        else if(group.getTag().equals("Art")){
            art.setTotal(art.getTotal() + (checkedID - ((group.getId() - one) * 5)));
            System.out.println("wowArt" + art.getTotal());
        }
    }

    //compares the total of each field and places the corresponding string into compFOSList
    public ArrayList<String> getCompatibleField(){

        fosList.add(STEM);
        fosList.add(socSci);
        fosList.add(business);
        fosList.add(health);
        fosList.add(art);

        int highNum = fosList.get(0).getTotal();

        for(int i = 0; i < fosList.size()-1; i++) {
            int next = i + 1;
            if (highNum < fosList.get(next).getTotal()) {
                highNum = fosList.get(next).getTotal();
            }
        }
        for(FieldOfStudy fos: fosList){
            if(fos.getTotal() == highNum){
                compFOSList.add(fos.getName());
            }
        }
        return compFOSList;
    }

}
