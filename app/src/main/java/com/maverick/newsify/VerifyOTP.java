package com.maverick.newsify;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class VerifyOTP extends AppCompatActivity {
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    PinView pinFromUser;
    String codeBySystem;

    String fullName, email, username, password, date, gender, phoneNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        pinFromUser = findViewById(R.id.pin_view);

        fullName = getIntent().getStringExtra("fullName");
        email = getIntent().getStringExtra("email");
        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");
        date = getIntent().getStringExtra("date");
        gender = getIntent().getStringExtra("gender");
        phoneNo = getIntent().getStringExtra("phoneNo");


        sendVerificationCodeToUser(phoneNo);
    }

    private void sendVerificationCodeToUser(String phoneNo) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phoneNo)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeBySystem = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                pinFromUser.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(VerifyOTP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        storeNewUsersData();
                    } else {
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(VerifyOTP.this, "Verification Not Completed", Toast.LENGTH_SHORT).show();
                        }
                    }


                });
    }
        private void storeNewUsersData (){

            FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
            DatabaseReference reference = rootNode.getReference("Users");
            UserHelperClass addNewUser = new UserHelperClass(fullName, email, username, password, date, gender, phoneNo);
            reference.setValue(addNewUser);
        }


        public void callNextScreenFromOTP (View view){
            String code = pinFromUser.getText().toString();
            if (!code.isEmpty()) {
                verifyCode(code);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Pair[] pairs = new Pair[1];

                pairs[0] = new Pair<View, String>(findViewById(R.id.main), "transition_signup");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(VerifyOTP.this, pairs);
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }
        }

    }
