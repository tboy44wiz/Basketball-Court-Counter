package com.example.android.b_bcourtcounter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
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

public class FinalResultActivity extends Activity {

    private TextView finalTeamAName, finalTeamBName, teamAScoreTextView, teamBScoreTextView, teamAFoulCountTextView, teamBFoulCountTextView;
    private String finalTeamAString, finalTeamBString, teamAScoreString, teamBScoreString;
    private String teamAFoulString, teamBFoulString;

    Vibrator fvibrator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_result);

        initializerForWidgets();

        fvibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        /*
        * Receive an Intent from the First Activity and save it in a String Variable.
        * */
            Bundle bundle = getIntent().getExtras();

            finalTeamAString = bundle.getString("teamANameKey");
            finalTeamBString = bundle.getString("teamBNameKey");
            teamAScoreString = bundle.getString("teamAScoreKey");
            teamBScoreString = bundle.getString("teamBScoreKey");
            teamAFoulString = Integer.toString(bundle.getInt("teamAFoulCounterKey"));   //One way of converting integer to String. [Integer.toString()]
            teamBFoulString = String.valueOf(bundle.getInt("teamBFoulCounterKey"));     //Another way of converting integer to String. [String.valueOf()]


        /*
        * Set the converted String to the Team Name TextView.
        * */
            finalTeamAName.setText(finalTeamAString);
            finalTeamBName.setText(finalTeamBString);
            teamAScoreTextView.setText(teamAScoreString);
            teamBScoreTextView.setText(teamBScoreString);
            teamAFoulCountTextView.setText(String.valueOf(teamAFoulString));
            teamBFoulCountTextView.setText(String.valueOf(teamBFoulString));

    }

    private void initializerForWidgets() {
        finalTeamAName = findViewById(R.id.teamAName_TextView);
        finalTeamBName = findViewById(R.id.teamBName_TextView);
        teamAScoreTextView = findViewById(R.id.teamAScore_TextView);
        teamAFoulCountTextView = findViewById(R.id.teamAFoulsScores_TextView);
        teamBScoreTextView = findViewById(R.id.teamBScore_TextView);
        teamBFoulCountTextView = findViewById(R.id.teamBFoulsScores_TextView);
    }

    public void close(View view) {
        fvibrator.vibrate(200);
        closeDialogOption();
    }

    private void closeDialogOption() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(FinalResultActivity.this);

        builder.setIcon(R.drawable.close);
        builder.setTitle("Close the game");
        builder.setMessage("Are you sure you want to close this game?");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FinalResultActivity.this.finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void share_ImageButton(View view) {
        fvibrator.vibrate(50);
        String teamAScoresString = (String) teamAScoreTextView.getText();
        String teamAFoulString = (String) teamAFoulCountTextView.getText();
        String teamBScoresString = (String) teamBScoreTextView.getText();
        String teamBFoulString = (String) teamBFoulCountTextView.getText();

        String messageToShare = "The game ended with: \n" + finalTeamAString + "= " + teamAScoresString + " with " + teamAFoulString + " foul(s)" + "\n"
                 + finalTeamBString + "= "+ teamBScoresString + " with " + teamBFoulString + " foul(s)";

        Intent messageToShareIntent = new Intent(Intent.ACTION_SEND);
        messageToShareIntent.setType("text/plain");

        messageToShareIntent.putExtra(Intent.EXTRA_TEXT,messageToShare);

        startActivity(Intent.createChooser(messageToShareIntent, "Share this result with:"));
    }


}
