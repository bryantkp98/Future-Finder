package com.example.futurefinder;


import static android.content.Intent.getIntent;

import static androidx.core.app.PendingIntentCompat.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.os.Bundle;

import java.util.ArrayList;

public class CollegeResultsScreen extends AppCompatActivity{
    TextView resultView;
    String resultText= "";
    String userCode = "";
    String parentClass = "";
    ArrayList<College> listOfC = new ArrayList<College>();
    ArrayList<College> finalArray = new ArrayList<College>();
    String[] userArry;
    ArrayList<Boolean> boolList = new ArrayList<Boolean>();

    /**
     * This is where all colleges will be stored
     */
    College VCU = new College("Art", 31360, "Urban", 24058, "Public", "Virginia Commonwealth University", "ehlop");
    College ODU = new College("Health", 16000, "Rural", 19606, "Public", "Old Dominion University", "dfjop");
    College GMU = new College("Business", 19000, "Suburban", 23179, "Public", "George Mason University", "cfkop");
    College VTECH = new College("STEM", 19000, "Rural", 25175, "Public", "Virginia Polytechnic University", "afjop");
    College JMU = new College("Health", 20000, "Rural", 19262, "Public", "James Madison University", "bfjop");
    College HAMPTON = new College("STEM", 34000, "Urban", 3793, "Private", "Hampton University", "ahlmq");
    College LU = new College("Business", 25000, "Suburban", 46680, "Private", "Liberty University", "cgkoq");
    College URVA = new College("Business", 25000, "Suburban", 3229, "Private", "University of Richmond", "cgkmq");
    College RADFORD = new College("Social Science", 17000, "Rural", 8426, "Public", "Radford University", "bfjnp");
    College UVA = new College("STEM", 19000, "Urban", 15963, "Public", "University of Virginia", "aflop");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_results_screen);
        resultView = resultView.findViewById(R.id.resultView);
        userCode = getIntent().getExtras().getString("userCode");
        parentClass = getIntent().getExtras().getString("parentClass");
        userArry  = userCode.split(",");

        //array list of all colleges
        listOfC.add(VCU);
        listOfC.add(ODU);
        listOfC.add(GMU);
        listOfC.add(VTECH);
        listOfC.add(JMU);
        listOfC.add(HAMPTON);
        listOfC.add(LU);
        listOfC.add(URVA);
        listOfC.add(RADFORD);
        listOfC.add(UVA);

        for(College x: listOfC){
            for(int i=0; i<userArry.length; i++){
                if ((!userArry[i].equals("")) && userArry[i].contains(Character.toString(x.getCode().charAt(i)))){
                    boolList.add(true);
                }
                else if(!userArry[i].equals("")){
                    boolList.add(false);
                }
            }
            if(!boolList.contains(false)){
                finalArray.add(x);
            }

            boolList.clear();
        }


        for(College x:finalArray){
            resultText+= "\n"+  ">>>" + x.getName() + "\n"+ "Main Field of study : " + x.getFieldOfStudy()+ "\n"+ "Tution and Fees Per Year : " + x.getTuition()+ "\n"+ "Environment : " + x.getEnvironment()+ "\n"+ "Size : " + x.getSize() + "\n"+ "Type : " + x.getType() + "\n" +"\n";
        }

        resultView.setText(resultText);

    }

    public void goBack(View view) {
        if (parentClass.equals("CollegeSelectionScreen")){
            Intent intent = new Intent(CollegeResultsScreen.this, CollegeSelectionScreen.class);
            startActivity(intent);
        }
        else if(parentClass.equals("MajorResultsScreen")) {
            Intent intent = new Intent(CollegeResultsScreen.this, MajorResultsScreen.class);
            startActivity(intent);
        }
    }
}
