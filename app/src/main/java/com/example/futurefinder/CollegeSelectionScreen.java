package com.example.futurefinder;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

import android.os.Bundle;
import android.widget.CheckBox;

public class CollegeSelectionScreen extends AppCompatActivity {

    String userCode = "";
    String parentClass = "CollegeSelectionScreen";

    CheckBox stem ;
    CheckBox socialScience;
    CheckBox business ;
    CheckBox health ;
    CheckBox art ;
    CheckBox zeroToFive ;
    CheckBox fiveToTen ;
    CheckBox tenToTwenty ;
    CheckBox twentyPlus ;
    CheckBox rural ;
    CheckBox subUrban ;
    CheckBox urban ;
    CheckBox small ;
    CheckBox medium ;
    CheckBox large ;
    CheckBox publicC ;
    CheckBox privateC ;
    Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_selection_screen);
        backButton = findViewById(R.id.backButton);
         stem = findViewById(R.id.stemBox);
         socialScience = findViewById(R.id.socialScienceBox);
         business = findViewById(R.id.businessBox);
         health = findViewById(R.id.healthBox);
         art = findViewById(R.id.artBox);
         zeroToFive = findViewById(R.id.zeroToTwenty);
         fiveToTen = findViewById(R.id.twentyToThirty);
         tenToTwenty = findViewById(R.id.thirtyToForty);
         twentyPlus = findViewById(R.id.fortyPlus);
         rural = findViewById(R.id.ruralBox);
         subUrban = findViewById(R.id.suburbanBox);
         urban = findViewById(R.id.urbanBox);
         small = findViewById(R.id.smallBox);
         medium = findViewById(R.id.mediumBox);
         large = findViewById(R.id.largeBox);
         publicC = findViewById(R.id.publicBox);
         privateC = findViewById(R.id.privateBox);
    }

    public void goBack(View view)
    {
        Intent intent = new Intent(CollegeSelectionScreen.this , MainActivity.class);
        startActivity(intent);
    }

    //creates a userCode to be compared to the college code
    public void Next(View view){

        userCode="";

        if(stem.isChecked())
        {
            userCode = userCode + "a";
        }
        if(socialScience.isChecked())
        {
            userCode = userCode + "b";
        }
        if(business.isChecked())
        {
            userCode = userCode + "c";
        }
        if(health.isChecked())
        {
            userCode = userCode + "d";
        }
        if(art.isChecked())
        {
            userCode = userCode + "e";
        }
        userCode+=",";
        if(zeroToFive.isChecked())
        {
            userCode = userCode + "f";
        }
        if(fiveToTen.isChecked())
        {
            userCode = userCode + "g";
        }
        if(tenToTwenty.isChecked())
        {
            userCode = userCode + "h";
        }
        if(twentyPlus.isChecked())
        {
            userCode = userCode + "i";
        }
        userCode+=",";
        if(rural.isChecked())
        {
            userCode = userCode + "j";
        }
        if(subUrban.isChecked())
        {
            userCode = userCode + "k";
        }
        if(urban.isChecked())
        {
            userCode = userCode + "l";
        }
        userCode+=",";
        if(small.isChecked())
        {
            userCode = userCode + "m";
        }
        if(medium.isChecked())
        {
            userCode = userCode + "n";
        }
        if(large.isChecked())
        {
            userCode = userCode + "o";
        }
        userCode+=",";
        if(publicC.isChecked())
        {
            userCode = userCode + "p";
        }
        if(privateC.isChecked())
        {
            userCode = userCode + "q";
        }

        Bundle extras = new Bundle();
        extras.putString("userCode", userCode);
        extras.putString("parentClass", parentClass);
        Intent intentNext = new Intent(CollegeSelectionScreen.this , CollegeResultsScreen.class);
        intentNext.putExtras(extras);
        startActivity(intentNext);

    }

    public void setUserCode(){
        //change to switch cases
    }

}
