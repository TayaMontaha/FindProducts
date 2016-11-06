package com.kl.montaha.findproducts;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private EditText etEmail;
    private EditText etPass1;
    private EditText etPass2;
    private EditText etFulName;
    private Button btnSave;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etEmail = (EditText) findViewById(R.id.etemail1);
        etFulName = (EditText) findViewById(R.id.etname);
        etPass1 = (EditText) findViewById(R.id.etpass1);
        etPass2 = (EditText) findViewById(R.id.etpass2);
        btnSave = (Button) findViewById(R.id.btnsave);
    }

    private void dataHandler() {
        boolean isok = true;
        String stEmail1 = etEmail.getText().toString();
        if (stEmail1.length() == 0) {
            etEmail.setError("Wrong Email");
            isok = false;
        }
        String stPass1 = etPass1.getText().toString();
        if (stPass1.length() == 0) {

            etPass1.setError("wrong password");
            isok = false;
        }
        String stPass2 = etPass2.getText().toString();
        if (stPass2.length() == 0) {

            etPass2.setError("not the same password");
            isok = false;
        }
        String stFulName = etFulName.getText().toString();
        if (stFulName.length() == 0) {
            etFulName.setError("there's no name");
            isok = false;
        }
        if (isok) {
            creatAcount(stEmail1, stPass1);
        }

    }

    private void eventHandler() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(SignUpActivity.this, Products.class);
                startActivity(i2);
                dataHandler();
            }
        });

    }

    private FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            //4.
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                //user is signed in
                Toast.makeText(SignUpActivity.this, "user is signed in.", Toast.LENGTH_SHORT).show();

            } else {
                //user signed out
                Toast.makeText(SignUpActivity.this, "user signed out.", Toast.LENGTH_SHORT).show();

            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null)
            auth.removeAuthStateListener(authStateListener);
    }

    private void creatAcount(String email, String passw) {
        auth.createUserWithEmailAndPassword(email, passw).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Authentication Successful.", Toast.LENGTH_SHORT).show();
                    //updateUserProfile(task.getResult().getUser());
                    finish();
                } else {
                    Toast.makeText(SignUpActivity.this, "Authentication failed." + task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();

                }
            }
        });
    }
}


