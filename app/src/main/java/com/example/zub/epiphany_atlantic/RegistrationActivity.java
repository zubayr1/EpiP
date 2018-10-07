package com.example.zub.epiphany_atlantic;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RegistrationActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT =5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {


                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new RegistrationFragment()).commit();

            }
        }, SPLASH_TIME_OUT);
    }
}
