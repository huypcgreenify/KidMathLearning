package com.kml.KidsMathLearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.numberprogressbar.NumberProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MHChaoGameActivity extends AppCompatActivity {
    Button btnBDG;
    private int counter;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        final NumberProgressBar bnp = (NumberProgressBar) findViewById(R.id.number_progress_bar);
        counter = 0;
        timer = new Timer();
        btnBDG = findViewById(R.id.btnBatDauGame);
        btnBDG.setVisibility(View.INVISIBLE);
        btnBDG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGame = new Intent(MHChaoGameActivity.this, MHChinhGameActivity.class);
                startActivity(intentGame);
            }
        });
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bnp.incrementProgressBy(1);
                        counter++;
                        if (counter == 100) {
                            bnp.setProgress(0);
                            counter = 0;
                            bnp.setVisibility(View.INVISIBLE);
                            btnBDG.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        }, 100, 10);
    }

}