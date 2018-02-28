package com.example.android.b_bcourtcounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by Osondu Tochukwu Andrew
 * on 1/17/2018.
 * Contact Email: tosolife@yahoo.com
 * Contact Phone No: 0803680243(WhatsApp), 08117042121.
 * Github: https://github.com/tboy44wiz
 * Linkedin: https://www.linkedin.com/in/osondu-tochukwu-81359a96
 */

public class MainActivity extends AppCompatActivity {

    private Spinner teamASpinner, teamBSpinner;
    Button submitButton;

    String[] teamAName = {"Home Team","New York Knicks","Atlanta Hawks","Golden State Warriors",
            "Charlotte Hornets","Toronto Raptors","Boston Celtics","Chicago Bulls","Denver Nuggets",
            "Houston Rockets", "Washington Wizards"};

    String[] teamBName = {"Away Team","New York Knicks","Atlanta Hawks","Golden State Warriors",
            "Charlotte Hornets","Toronto Raptors","Boston Celtics","Chicago Bulls","Denver Nuggets",
            "Houston Rockets", "Washington Wizards"};

    int[] teamLogos = {R.drawable.ic_launcher1, R.drawable.new_york_knicks, R.drawable.atlanta_hawks,
            R.drawable.golden_warriors, R.drawable.charlotte_hornet, R.drawable.toronto_raptors, R.drawable.boston_celtic,
            R.drawable.chicago_bulls, R.drawable.denver_nugget, R.drawable.houston_rocket, R.drawable.washington_wizards};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teamASpinner = findViewById(R.id.teamA_Spinner);
        teamBSpinner = findViewById(R.id.teamB_Spinner);
        submitButton = findViewById(R.id.submit_Button);

        /*
        * Set the CustomSpinnerAdapter inside customSpinnerAdapter() method.
        * */
        customSpinnerAdapter();

        /*
        * Add a Listener Event to the Button Clicked.
        * */
        addListenerToButtonClicked();

    }

    public void customSpinnerAdapter() {
        CustomSpinnerAdapter customTeamASpinnerAdapter = new CustomSpinnerAdapter(MainActivity.this, teamAName, teamLogos);
        CustomSpinnerAdapter customTeamBSpinnerAdapter = new CustomSpinnerAdapter(MainActivity.this, teamBName, teamLogos);
        teamASpinner.setAdapter(customTeamASpinnerAdapter);
        teamBSpinner.setAdapter(customTeamBSpinnerAdapter);


        teamASpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        teamBSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /*
    * Add a Listener Event to the Button Clicked.
    * */
    public void addListenerToButtonClicked() {


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                * Get Selected items from the Spinner View.
                * */
                String teamA = teamASpinner.getSelectedItem().toString();
                String teamB = teamBSpinner.getSelectedItem().toString();
                /*
                * Start a new Intent
                * */
                Intent sentIntent = new Intent(MainActivity.this, CounterActivity.class);

                /*
                * Sending Data to the Second Activity
                * */
                sentIntent.putExtra("teamAKey", teamA);
                sentIntent.putExtra("teamBKey", teamB);

                startActivity(sentIntent);
            }
        });
    }

}
