package com.example.fiveinarow.ui.GameTwo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fiveinarow.GameFinishedFragment;
import com.example.fiveinarow.MainActivity;
import com.example.fiveinarow.R;

import java.util.Arrays;



public class GameTwoActivity extends AppCompatActivity implements GameTwoFragment.GTInterface, GameFinishedFragment.FinishInterface {

    private final String TAG = GameTwoActivity.class.toString();

    GameTwoFragment gameTwoFragment;
    GameFinishedFragment gameFinishedFragment;


    boolean turn = false;
    int turnCounter = 0;
    int[][] buttons;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_gametwo);

        gameTwoFragment = new GameTwoFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.containerGameTwo, gameTwoFragment).commit();


        buttons = new int[13][13];
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        GameTwoActivity.super.onBackPressed();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        finish();
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                }).create().show();
    }

    @Override
    public void buttonClicked(int row, int column) {

        turnCounter++;

        //turn = boy = true = 1 = red
        if (turn) {
            buttons[row][column] = 1;
            Log.d(TAG, "Activity: button boy Clicked");
            Log.d(TAG, Arrays.deepToString(buttons));
        }
        // turn = girl = false = 2 = black
        else {
            buttons[row][column] = 2;
            Log.d(TAG, "Activity: button girl Clicked");
            Log.d(TAG, Arrays.deepToString(buttons));
        }
        gameTwoFragment.addBall(turn, row, column);

        if (checkFive(turn)) return;

        if (turnCounter >= 169) {
            finishGame(0);
        }

        turn = !turn;
        gameTwoFragment.changeTurn(turn);
    }

    private boolean checkFive(boolean t) {
        // t = true -> boy´s turn
        if (t) {
            if (fiveRow(1) || fiveColumn(1) || fiveLDiagonal(1) || fiveRDiagonal(1)) {
                finishGame(1);
                return true;
            }
        }

        // t = false -> girl´s turn
        else {
            if (fiveRow(2) || fiveColumn(2) || fiveLDiagonal(2) || fiveRDiagonal(2)) {
                finishGame(2);
                return true;
            }
        }
        return false;

    }

    //Diagonal to bottom-right
    private boolean fiveRDiagonal(int t) {
        int inRDiagonal = 0;
        for (int row = 0; row < buttons.length - 4; row++) {
            for (int column = 0; column < buttons.length - 4; column++) {
                try {
                    for (int i = 0; i < 5; i++) {
                        if (buttons[row + i][column + i] == t)
                            inRDiagonal++;
                        else inRDiagonal = 0;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    Log.d(TAG, "RDiagonal: Index out of Bounds: [" + row + "][" + column + "]");
                }
                if (inRDiagonal == 5) {
                    Log.d(TAG, "RDiagonal");
                    return true;
                }
            }
            inRDiagonal = 0;
        }
        return false;
    }

    //Diagonal to bottom-left
    private boolean fiveLDiagonal(int t) {
        int inLDiagonal = 0;
        for (int row = 0; row < buttons.length - 4; row++) {
            for (int column = 4; column < buttons.length; column++) {
                try {
                    for (int i = 0; i < 5; i++) {
                        if (buttons[row + i][column - i] == t)
                            inLDiagonal++;
                        else inLDiagonal = 0;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    Log.d(TAG, "LDiagonal: Index out of Bounds: [" + row + "][" + column + "]");
                }
                if (inLDiagonal == 5) {
                    Log.d(TAG, "LDiagonal");
                    return true;
                }
            }
            inLDiagonal = 0;
        }
        return false;
    }

    private boolean fiveRow(int t) {
        int inARow = 0;
        for (int row = 0; row < buttons.length; row++) {
            for (int column = 0; column < buttons.length; column++) {
                if (buttons[row][column] == t)
                    inARow++;
                else inARow = 0;
                if (inARow == 5) {
                    Log.d(TAG, "ROW");
                    return true;
                }
            }
            inARow = 0;
        }
        return false;
    }

    private boolean fiveColumn(int t) {
        int inAColumn = 0;
        for (int column = 0; column < buttons.length; column++) {
            for (int row = 0; row < buttons.length; row++) {
                if (buttons[row][column] == t)
                    inAColumn++;
                else inAColumn = 0;
                if (inAColumn == 5) {
                    Log.d(TAG, "Column");
                    return true;
                }
            }
            inAColumn = 0;
        }
        return false;
    }


    private void finishGame(int winner) {
        Log.d(TAG, "The Winner is: " + winner);

        gameTwoFragment.finishGame(winner);
        gameFinishedFragment = new GameFinishedFragment(winner);
        getSupportFragmentManager().beginTransaction().replace(R.id.containerGameTwo, gameFinishedFragment).commit();

    }

    @Override
    public void closeClicked() {
        onBackPressed();
    }

    @Override
    public void backClicked() {
        Toast.makeText(getApplicationContext(), "not yet implemented", Toast.LENGTH_SHORT);
    }

    @Override
    public void newGameClicked() {
        Intent i = new Intent(this, GameTwoActivity.class);
        finish();
        startActivity(i);
    }

    @Override
    public void homeClicked() {
        Intent i = new Intent(this, MainActivity.class);
        finish();
        startActivity(i);

    }
}
