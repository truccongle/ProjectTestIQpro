package com.example.truccongle.projecttestiq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    DatabaseHandler db=new DatabaseHandler(this);
Button btnStart,btnHighScore,btnInfo,btnSet,btnAbout,btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            db.copyDB2SDCard();
        } catch (IOException e) {
            e.printStackTrace();
        }
        btnStart =(Button) findViewById(R.id.btnStart);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        btnStart.startAnimation(animation1);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                btnStart.startAnimation(animation1);
                Intent intent= new Intent(MainActivity.this,AgeActivity.class);
                startActivity(intent);
            }
        });
        btnHighScore=(Button)findViewById(R.id.btnHighScore);
        btnHighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,HighScoreActivity.class);
                startActivity(intent);
            }
        });
        btnInfo=(Button)findViewById(R.id.btnInfo);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,InfoActivity.class);
                startActivity(intent);
            }
        });
        btnAbout=(Button)findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,WelcomeActivity.class);
                startActivity(intent);
            }
        });
        btnSet=(Button)findViewById(R.id.btnSet);
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,SettingActivity.class);
                startActivity(intent);
            }
        });



    }
}
