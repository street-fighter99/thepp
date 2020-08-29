package com.example.thepp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class first1 extends AppCompatActivity {
    Button generateotp;
    EditText phno;
    ProgressBar gbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first1);
        generateotp=findViewById(R.id.btt);
        gbar=findViewById(R.id.progressBar2);
        phno=findViewById(R.id.editText);
        generateotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone=phno.getText().toString();
                if(phone.isEmpty())
                {
                    phno.setError("enter your phone no:");
                    phno.requestFocus();
                }
                else if(phno.length()!=10)
                {
                    phno.setError("check your phone number");
                    phno.requestFocus();
                }  else {
                    gbar.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(getApplicationContext(), otpPage.class);
                    intent.putExtra("phone",phone);
                    startActivity(intent);
                }

            }
        });
    }
}