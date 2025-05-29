package com.example.futurefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

public class JobSearchScreen extends AppCompatActivity {

    private Button filterButton;
    private Button searchButton;
    private EditText jobText;
    private String jobTypeFilt = "";
    private String stateFilt = "";
    private PopupMenu popup;


    //creates onClickListener for filterButton that opens up the popup menu, creates an onMenuItemClickListener
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_search_screen);
        filterButton = findViewById(R.id.filterButton);
        searchButton = findViewById(R.id.jobSearchButton);

        @SuppressLint("UseCompatLoadingForDrawables") Drawable background = getDrawable(R.drawable.rounded_button); // new
        filterButton.setBackground(background);
        searchButton.setBackground(background);
        jobText = findViewById(R.id.jobText);
        popup = new PopupMenu(this, filterButton);
        popup.getMenuInflater().inflate(R.menu.job_filter_menu, popup.getMenu());
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup.show();

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        return onFilterMenuItemClick(menuItem);
                    }
                });
            }
        });
    }

    //shows toast if nothing has been typed in search bother, otherwise sends scraper needed variables
    public void searchButton (View view){
        if(jobText.getText().length() > 0){
            String jobSearch = jobText.getText().toString();
            Bundle extras = new Bundle();
            extras.putString("jobSearch", jobSearch);
            extras.putString("stateFilt", stateFilt);
            extras.putString("jobTypeFilt", jobTypeFilt);

            Intent intent = new Intent(this, JobResultsScreen.class);
            intent.putExtras(extras);
            startActivity(intent);
        }
        else{
            Toast t = Toast.makeText(this, "CANNOT LEAVE SEARCH BAR BLANK", Toast.LENGTH_LONG);
            t.show();
        }
    }

    //formats a toast message depending on the user's filter choices
    public void showToastMssg(){
        String toastMssg;
        if(jobTypeFilt.length() == 0 && stateFilt.length() == 0){
            toastMssg = "No Current Search Filters";
        }
        else if(!(jobTypeFilt.length() > 0)){
            toastMssg = "Searching jobs in " + stateFilt;
        }
        else if(!(stateFilt.length() > 0)){
            toastMssg = "Searching for " + jobTypeFilt;
        }
        else {
            toastMssg = "Searching for " + jobTypeFilt + " in " + stateFilt;
        }
        Toast t = Toast.makeText(this, toastMssg, Toast.LENGTH_LONG);
        t.show();
    }

    //handles menu click
    public boolean onFilterMenuItemClick(MenuItem item) {
        // Handle item selection
        item.setChecked(!item.isChecked());
        switch (item.getItemId()) {
            case R.id.jobTypeDefault:
                jobTypeFilt = "";
                showToastMssg();
                return true;
            case R.id.fullTimeJob:
                jobTypeFilt = "Full-Time Job";
                showToastMssg();
                return true;
            case R.id.partTimeJob:
                jobTypeFilt = "Part-Time Job";
                showToastMssg();
                return true;
            case R.id.internship:
                jobTypeFilt = "Internship";
                showToastMssg();
                return true;
            case R.id.locationDefault:
                stateFilt = "";
                showToastMssg();
                return true;
            case R.id.alaska:
                stateFilt = "AK";
                showToastMssg();
                return true;
            case R.id.alabama:
                stateFilt = "AL";
                showToastMssg();
                return true;
            case R.id.arkansas:
                stateFilt = "AR";
                showToastMssg();
                return true;
            case R.id.arizona:
                stateFilt = "AZ";
                showToastMssg();
                return true;
            case R.id.california:
                stateFilt = "CA";
                showToastMssg();
                return true;
            case R.id.colorado:
                stateFilt = "CO";
                showToastMssg();
                return true;
            case R.id.connecticut:
                stateFilt = "CT";
                showToastMssg();
                return true;
            case R.id.delaware:
                stateFilt = "DE";
                showToastMssg();
                return true;
            case R.id.florida:
                stateFilt = "FL";
                showToastMssg();
                return true;
            case R.id.georgia:
                stateFilt = "GA";
                showToastMssg();
                return true;
            case R.id.hawaii:
                stateFilt = "HI";
                showToastMssg();
                return true;
            case R.id.iowa:
                stateFilt = "IA";
                showToastMssg();
                return true;
            case R.id.idaho:
                stateFilt = "ID";
                showToastMssg();
                return true;
            case R.id.illinois:
                stateFilt = "IL";
                showToastMssg();
                return true;
            case R.id.indiana:
                stateFilt = "IN";
                showToastMssg();
                return true;
            case R.id.kansas:
                stateFilt = "KS";
                showToastMssg();
                return true;
            case R.id.kentucky:
                stateFilt = "KY";
                showToastMssg();
                return true;
            case R.id.louisiana:
                stateFilt = "LA";
                showToastMssg();
                return true;
            case R.id.massachusetts:
                stateFilt = "MA";
                showToastMssg();
                return true;
            case R.id.maryland:
                stateFilt = "MD";
                showToastMssg();
                return true;
            case R.id.maine:
                stateFilt = "ME";
                showToastMssg();
                return true;
            case R.id.michigan:
                stateFilt = "MI";
                showToastMssg();
                return true;
            case R.id.minnesota:
                stateFilt = "";
                stateFilt = "MN";
                showToastMssg();
                return true;
            case R.id.missouri:
                stateFilt = "MO";
                showToastMssg();
                return true;
            case R.id.mississippi:
                stateFilt = "MS";
                showToastMssg();
                return true;
            case R.id.montana:
                stateFilt = "MT";
                showToastMssg();
                return true;
            case R.id.northCarolina:
                stateFilt = "NC";
                showToastMssg();
                return true;
            case R.id.northDakota:
                stateFilt = "ND";
                showToastMssg();
                return true;
            case R.id.nebraska:
                stateFilt = "NE";
                showToastMssg();
                return true;
            case R.id.newHamshire:
                stateFilt = "NH";
                showToastMssg();
                return true;
            case R.id.newJersey:
                stateFilt = "NJ";
                showToastMssg();
                return true;
            case R.id.newMexico:
                stateFilt = "NM";
                showToastMssg();
                return true;
            case R.id.nevada:
                stateFilt = "NV";
                showToastMssg();
                return true;
            case R.id.newYork:
                stateFilt = "NY";
                showToastMssg();
                return true;
            case R.id.ohio:
                stateFilt = "OH";
                showToastMssg();
                return true;
            case R.id.oklahoma:
                stateFilt = "OK";
                showToastMssg();
                return true;
            case R.id.oregon:
                stateFilt = "OR";
                showToastMssg();
                return true;
            case R.id.pennsylvania:
                stateFilt = "PA";
                showToastMssg();
                return true;
            case R.id.rhodeIsland:
                stateFilt = "RI";
                showToastMssg();
                return true;
            case R.id.southCarolina:
                stateFilt = "SC";
                showToastMssg();
                return true;
            case R.id.southDakota:
                stateFilt = "SD";
                showToastMssg();
                return true;
            case R.id.tennessee:
                stateFilt = "TN";
                showToastMssg();
                return true;
            case R.id.texas:
                stateFilt = "TX";
                showToastMssg();
                return true;
            case R.id.utah:
                stateFilt = "UT";
                showToastMssg();
                return true;
            case R.id.virginia:
                stateFilt = "VA";
                showToastMssg();
                return true;
            case R.id.vermont:
                stateFilt = "VT";
                showToastMssg();
                return true;
            case R.id.washington:
                stateFilt = "WA";
                showToastMssg();
                return true;
            case R.id.wisconsin:
                stateFilt = "WI";
                showToastMssg();
                return true;
            case R.id.westVirginia:
                stateFilt = "WV";
                showToastMssg();
                return true;
            case R.id.wyoming:
                stateFilt = "WY";
                showToastMssg();
                return true;
            default:
                return false;
        }

    }

}
