package com.example.thepp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity {
    FirebaseAuth fire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        fire=FirebaseAuth.getInstance();
        if(fire.getCurrentUser()!=null)
        {
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    startActivity(new Intent(getApplicationContext(),home.class));
                    finish();
                }


            },4000);

        }else{
            startActivity(new Intent(getApplicationContext(),first1.class));
            finish();
        }

    }
}