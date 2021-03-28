package com.example.fiveinarow;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.fiveinarow.ui.GameTwo.GameTwoActivity;
import com.example.fiveinarow.ui.home.HomeFragment;



public class MainActivity extends AppCompatActivity implements HomeFragment.StartGame {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void startGame(int i) {
        Intent intent;
        switch (i) {
            //case 1:  intent = new Intent(getApplicationContext(), GameOneActivity.class); break;
            case 2:
                intent = new Intent(getApplicationContext(), GameTwoActivity.class);
                break;
            default:
                intent = null;
        }

        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


}