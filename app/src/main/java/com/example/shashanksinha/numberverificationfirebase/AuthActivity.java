package com.example.shashanksinha.numberverificationfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class AuthActivity extends AppCompatActivity {

    private LinearLayout mPhoneLayout;
    private LinearLayout mCodeLayout;

    private EditText mPhoneText;
    private EditText mCodeText;

    private ProgressBar mPhoneBar;
    private ProgressBar mCodeBar;

    private Button mSendButton;

    private FirebaseAuth mAuth;

    private String codeSent;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);


        //SETTING UP UI
        mPhoneLayout = findViewById(R.id.phone_layout);
        mCodeLayout = findViewById(R.id.code_layout);

        mPhoneText = findViewById(R.id.phone_editText);
        mCodeText = findViewById(R.id.code_editText);

        mPhoneBar = findViewById(R.id.phone_progressBar);
        mCodeBar = findViewById(R.id.code_progressBar);

        mSendButton = findViewById(R.id.send_btn);

        mAuth = FirebaseAuth.getInstance();

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPhoneBar.setVisibility(View.VISIBLE);
                mPhoneText.setEnabled(false);
                mSendButton.setEnabled(false);

                String phone_number = mPhoneText.getText().toString();

                if(phone_number != null)
                {
                    Log.i("xlr8",phone_number);

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            phone_number,
                            60,
                            TimeUnit.SECONDS,
                            AuthActivity.this,
                            mCallbacks
                    );
                }
                mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {

                    }

                    @Override
                    public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);

                        codeSent = s;
                    }
                };
            }
        });


    }
}
