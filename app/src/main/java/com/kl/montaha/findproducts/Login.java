package com.kl.montaha.findproducts;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private EditText etemail;
    private EditText etpassword;
    private Button btnforget;
    private Button btnSignUp;
    private Button btnSignin;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etemail = (EditText) findViewById(R.id.etEmail);
        etpassword = (EditText) findViewById(R.id.etPassword);
        btnforget = (Button) findViewById(R.id.btnForget);
        btnSignin = (Button) findViewById(R.id.btnSignin);
        btnSignUp = (Button) findViewById(R.id.btnsignUp);
        auth = FirebaseAuth.getInstance();
    }

    private void dataHandler() {
        /**
         * getting Data
         */
        boolean isok=true;
        String stEmail = etemail.getText().toString();
        //2 Checking
        if (stEmail.length() == 0) {
            etemail.setError("wrong Email");
            isok=false;
        }
        String stPass = etpassword.getText().toString();
        if (stPass.length() == 0) {
            etpassword.setError("Wrong Password");
            isok = false;
        }
        if(isok)
        {
            signIn(stEmail,stPass);
        }
    }


    private void eventHandler() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Login.this, SignUpActivity.class);
                startActivity(i1);
            }
        });
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataHandler();
            }

        });
        btnforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

//*putting event for listner

    private void signIn(String email, String passw) {

        auth.signInWithEmailAndPassword(email,passw).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Intent i = new Intent(Login.this, Products.class);
                    startActivity(i);
                    Toast.makeText(Login.this, "signIn Successful.", Toast.LENGTH_SHORT).show();
                    // Intent intent=new Intent(LogInActivity.this,MainFCMActivity.class);
                    //   startActivity(intent);
                    //  finish();
                }
                else
                {
                    Toast.makeText(Login.this, "signIn failed."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
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
                Toast.makeText(Login.this, "user is signed in.", Toast.LENGTH_SHORT).show();

            } else {
                //user signed out
                Toast.makeText(Login.this, "user signed out.", Toast.LENGTH_SHORT).show();

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
}


