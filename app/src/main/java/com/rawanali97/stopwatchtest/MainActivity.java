package com.rawanali97.stopwatchtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int secand=0;
    private boolean running=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckSavedInstanceState(savedInstanceState);
        runTime();

    }

    private void CheckSavedInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState!=null){
           secand= savedInstanceState.getInt("secand");
           running=savedInstanceState.getBoolean("running");
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("secand",secand);
        outState.putBoolean("running",running);
    }

    private void runTime() {
        final TextView txtTime=findViewById(R.id.txtTime);
        final Handler handler =new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                  if (running){
                      secand++;
                  }
                  int hours= secand/3600 ;
                  int munets=(secand%3600)/60;
                  int secs=secand%60;
                  String time = hours+" : "+munets+" : "+secs;
                  txtTime.setText(time);
                  handler.postDelayed(this,1000);
            }
        });

    }



    public void btnOnClickStart(View view) {
        running=true;
    }

    public void btnOnClickStop(View view) {
      running=false;
    }

    public void btnOnClickReset(View view) {
        running=false;
        secand=0;
    }
}
