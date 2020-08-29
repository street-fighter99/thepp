package com.example.thepp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otpPage extends AppCompatActivity {
    String Vcode;
    Button chkotp;
    EditText otp;
    ProgressBar pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_page);
        chkotp=findViewById(R.id.otpbttn);
        otp=findViewById(R.id.editText);
        pro=findViewById(R.id.progressBar);
        String phoneno=getIntent().getStringExtra("phone");
        sendVerificationCodeToUser(phoneno);
    }


    private void sendVerificationCodeToUser(String phone){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+phone,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD, mCallbacks);

    }


    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            Vcode = s;
        }


        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                pro.setVisibility(View.VISIBLE);
                verifyCode(code);
            }

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(otpPage.this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }};

        private void verifyCode(String codeByUser) {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(Vcode, codeByUser);
            signinUserByCredential(credential);
        }


        private void signinUserByCredential(PhoneAuthCredential credential) {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.signInWithCredential(credential).addOnCompleteListener(otpPage.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(otpPage.this, "welcome", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),home.class));
                        finish();
                    } else {
                        Toast.makeText(otpPage.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }}