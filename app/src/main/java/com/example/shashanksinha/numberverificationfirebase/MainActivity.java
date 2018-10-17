package com.example.shashanksinha.numberverificationfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    // GETTING FIREBASE AUTH
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // INITIALIZING FIREBASE AUTH
        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        // CHECKING IF USER IS LOGGED IN
        if(currentUser == null)
        {
            Intent authIntent = new Intent(MainActivity.this,AuthActivity.class);
            startActivity(authIntent);
            finish();
        }
    }


}
