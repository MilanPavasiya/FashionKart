package com.example.jayghodasara.myyyyyy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    EditText Email, Password, Name;
    Button Register;
    String NameHolder, EmailHolder, PasswordHolder;
    Boolean EditTextEmptyHolder;
    FirebaseAuth auth;
    FirebaseDatabase f;
    DatabaseReference ref,ref2;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        f=FirebaseDatabase.getInstance();
        Register = (Button) findViewById(R.id.buttonSignin);
        Email = (EditText) findViewById(R.id.editEmail);
        Password = (EditText) findViewById(R.id.editPassword);
        Name = (EditText) findViewById(R.id.editName);
        auth = FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);

Register.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        String name=Name.getText().toString();
        String email=Email.getText().toString();
        String Pass=Password.getText().toString();
        Authentication();
        Intent i=new Intent(Signup.this,MainActivity.class);
        startActivity(i);

        ref=f.getReference("Users").child(name);
        ref.child("Name").push().setValue(name);
        ref.child("Email").push().setValue(email);
        ref.child("Password").push().setValue(Pass);


    }
});

    }

    private void Authentication()
    {
        String name=Email.getText().toString();
        String pass=Password.getText().toString();
        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();
        auth.createUserWithEmailAndPassword(name, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            finish();
                            Toast.makeText(Signup.this, "Your account is created", Toast.LENGTH_SHORT).show();


                        } else {
                            //display some message here
                            Toast.makeText(Signup.this, "Registration Error", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
}
