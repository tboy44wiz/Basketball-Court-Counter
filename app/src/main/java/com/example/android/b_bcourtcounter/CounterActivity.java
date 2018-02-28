package com.example.android.b_bcourtcounter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Osondu Tochukwu Andrew
 * on 1/17/2018.
 * Contact Email: tosolife@yahoo.com
 * Contact Phone No: 0803680243(WhatsApp), 08117042121.
 * Github: https://github.com/tboy44wiz
 * Linkedin: https://www.linkedin.com/in/osondu-tochukwu-81359a96
 */

public class CounterActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView teamATextView, teamBTextView, teamAScoreTextView, teamBScoreTextView;
    private String teamAString, teamBString;
    private Button endGameButton, resetButton;

    int teamAScores = 0;
    int teamBScores = 0;

    int foulCounterForTeamA = 0;
    int foulCounterForTeamB = 0;

    Vibrator vibrator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        initializer();

        endGameButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);


        /*
        * Receive an Intent from the First Activity and save it in a String Variable.
        * */
        Bundle receivedBundle = getIntent().getExtras();
        teamAString = receivedBundle.getString("teamAKey");
        teamBString = receivedBundle.getString("teamBKey");

        /*
        * Set the converted String to the Team Name TextView.
        * */
        teamATextView.setText(teamAString);
        teamBTextView.setText(teamBString);

        /*
        * Initialize the Vibrator.
        * */
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

    }

    private void initializer() {
        teamATextView = findViewById(R.id.teamA_TextView);
        teamBTextView = findViewById(R.id.teamB_TextView);
        teamAScoreTextView = findViewById(R.id.teamAScore_TextView);
        teamBScoreTextView = findViewById(R.id.teamBScore_TextView);
        endGameButton = findViewById(R.id.endGame_Button);
        resetButton = findViewById(R.id.reset_Button);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("teamAScoreSavedState", teamAScores);
        outState.putInt("teamBScoreSavedState", teamBScores);
        outState.putInt("teamAFoulSavedState", foulCounterForTeamA);
        outState.putInt("teamBFoulSavedState", foulCounterForTeamB);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        teamAScores = savedInstanceState.getInt("teamAScoreSavedState");
        teamBScores = savedInstanceState.getInt("teamBScoreSavedState");
        foulCounterForTeamA = savedInstanceState.getInt("teamAFoulSavedState");
        foulCounterForTeamB = savedInstanceState.getInt("teamBFoulSavedState");
        displayScoreForTeamA(teamAScores);
        displayScoreForTeamB(teamBScores);
    }

    /**
     * Add Three points for Team A.
     * */
    public void plusThreeForTeamA(View view) {
        teamAScores = teamAScores + 3;
        displayScoreForTeamA(teamAScores);
    }

    /**
     * Add Two points for Team A.
     * */
    public void plusTwoForTeamA(View view) {
        teamAScores = teamAScores + 2;
        displayScoreForTeamA(teamAScores);
    }

    /**
     * Add One point for Team A.
     * */
    public void plusOneForTeamA(View view) {
        teamAScores = teamAScores + 1;
        displayScoreForTeamA(teamAScores);
        foulCounterForTeamA = foulCounterForTeamA + 1;
    }




    /**
     * Add Three points for Team B.
     * */
    public void plusThreeForTeamB(View view) {
        teamBScores = teamBScores + 3;
        displayScoreForTeamB(teamBScores);
    }

    /**
     * Add Two points for Team B.
     * */
    public void plusTwoForTeamB(View view) {
        teamBScores = teamBScores + 2;
        displayScoreForTeamB(teamBScores);
    }

    /**
     * Add One point for Team B.
     * */
    public void plusOneForTeamB(View view) {
        teamBScores = teamBScores + 1;
        displayScoreForTeamB(teamBScores);
        foulCounterForTeamB = foulCounterForTeamB + 1;
        //Log.d("TAG", "plusOneForTeamB: Foul Counter " + foulCounterForTeamB);
    }



    /**
     * Display Scores for Team A.
     * */
    private void displayScoreForTeamA(int scores) {
        teamAScoreTextView.setText(String.valueOf(scores));
    }


    /**
     * Display Scores for Team B.
     * */
    private void displayScoreForTeamB(int score) {
        teamBScoreTextView.setText(String.valueOf(score));
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            /**
             * End the match.
             * */
            case R.id.endGame_Button:
                //Toast.makeText(getApplicationContext(), "Clicked working", Toast.LENGTH_SHORT).show();
                vibrator.vibrate(50);

                String getTeamAName = teamATextView.getText().toString();
                String getTeamBName = teamBTextView.getText().toString();

                String getScoreForTeamA = teamAScoreTextView.getText().toString();
                String getScoreForTeamB = teamBScoreTextView.getText().toString();

                if (getScoreForTeamA.isEmpty() && getScoreForTeamB.isEmpty() && foulCounterForTeamA == 0 && foulCounterForTeamB == 0){

                    Toast.makeText(getApplicationContext(), "Clicked working", Toast.LENGTH_SHORT).show();

                }
                else{
                    Intent viewFinalResultIntent = new Intent(CounterActivity.this, FinalResultActivity.class);
                    viewFinalResultIntent.putExtra("teamANameKey", getTeamAName);
                    viewFinalResultIntent.putExtra("teamBNameKey", getTeamBName);
                    viewFinalResultIntent.putExtra("teamAScoreKey", getScoreForTeamA);
                    viewFinalResultIntent.putExtra("teamBScoreKey", getScoreForTeamB);
                    viewFinalResultIntent.putExtra("teamAFoulCounterKey", foulCounterForTeamA);
                    viewFinalResultIntent.putExtra("teamBFoulCounterKey", foulCounterForTeamB);

                    startActivity(viewFinalResultIntent);

                    CounterActivity.this.finish();
                }
                break;

            /**
             * Reset Scores for Team A and Team B.
             * */
            case R.id.reset_Button:
                //ToDo list
                vibrator.vibrate(200);
                setDialogOption();
                break;
        }
    }


    private void setDialogOption() {

        //Instantiate an Alert Dialog Builder.
        final AlertDialog.Builder builder = new AlertDialog.Builder(CounterActivity.this);

        //Set the Title of the Dialog box.
        builder.setTitle("Confirm to Reset");

        //Set Dialog message.
        builder.setMessage("Are you sure to reset the scores?");

        //Set cancelable.
        builder.setCancelable(false);

        //Set Positive Button Clicked.
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                teamAScores = 0;
                teamBScores = 0;
                displayScoreForTeamA(teamAScores);
                displayScoreForTeamB(teamBScores);
                foulCounterForTeamA = 0;
                foulCounterForTeamB = 0;

            }
        });

        //Set Negative Button Clicked.
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        //Now let's create the Alert Dialog interface.
        AlertDialog alertDialog = builder.create();

        //Show the created Alert Dialog box.
        alertDialog.show();
    }

}
