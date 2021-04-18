package com.example.jayghodasara.myyyyyy;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button LogInButton;
    EditText Email,Password;
    TextView RegisterButton;
    String EmailHolder, PasswordHolder;
    ProgressDialog progressDialog;
    Boolean EditTextEmptyHolder;
    FirebaseAuth auth;
   // SQLiteDatabase sqLiteDatabaseObj;
   // SQLiteHelper sqLiteHelper;
    //Cursor cursor;
    String TempPassword = "NOT_FOUND" ;
    public static final String UserEmail = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogInButton = (Button)findViewById(R.id.buttonSignup);
        RegisterButton = (TextView) findViewById(R.id.textViewSignin);
        Email = (EditText)findViewById(R.id.editName);
        Password = (EditText)findViewById(R.id.editPassword);
progressDialog=new ProgressDialog(this);

        String name=Email.getText().toString();

        SharedPreferences s=this.getSharedPreferences("Values", Context.MODE_PRIVATE);
        SharedPreferences.Editor e=s.edit();
        e.putString("Name",name);

        e.commit();
        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null)
        {
            finish();

            startActivity(new Intent(getApplicationContext(), Home.class));
        }
        //sqLiteHelper = new SQLiteHelper(this);

        //Adding click listener to log in button.
        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticate();

                // Calling EditText is empty or no method.
               // CheckEditTextStatus();

                // Calling login method.
              //  LoginFunction();


            }
        });

        // Adding click listener to register button.
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Opening new user registration activity using intent on button click.
                Intent intent = new Intent(MainActivity.this, Signup.class);
                startActivity(intent);

            }
        });

    }

    public void authenticate()
    {
        progressDialog.setMessage("Checking Credentials");
        progressDialog.show();
      String name=Email.getText().toString();
        String password=Password.getText().toString();
        //logging in the user
        auth.signInWithEmailAndPassword(name, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){

                            finish();
                          //  SharedPreferences s=getSharedPreferences("Pref",MODE_PRIVATE);
                           // SharedPreferences.Editor editor=s.edit();
                            //editor.putString("Email",Email);
                            //editor.commit();
                            Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Home.class));
                        }
                        else
                            Toast.makeText(MainActivity.this, "Please Register First", Toast.LENGTH_SHORT).show();
                    }
                });

    }
    // Login function starts from here.
  /*  public void LoginFunction() {

        if (EditTextEmptyHolder) {

            // Opening SQLite database write permission.
            //  sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();

            //  // Adding search email query to cursor.
            //  cursor = sqLiteDatabaseObj.query(SQLiteHelper.TABLE_NAME, null, " " + SQLiteHelper.Table_Column_2_Email + "=?", new String[]{EmailHolder}, null, null, null);

           /* while (cursor.moveToNext()) {

                if (cursor.isFirst()) {

                    cursor.moveToFirst();

                    // Storing Password associated with entered email.
                    TempPassword = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_3_Password));

                    // Closing cursor.
                    cursor.close();
                }
            }

            // Calling method to check final result ..
            CheckFinalResult();

        }
        else {

            //If any of login EditText empty then this block will be executed.
            Toast.makeText(MainActivity.this,"Please Enter UserName or Password.",Toast.LENGTH_LONG).show();

        }*/




    // Checking EditText is empty or not.
    public void CheckEditTextStatus(){

        // Getting value from All EditText and storing into String Variables.
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();

        // Checking EditText is empty or no using TextUtils.
        if( TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)){

            EditTextEmptyHolder = false ;

        }
        else {

            EditTextEmptyHolder = true ;
        }
    }

    // Checking entered password from SQLite database email associated password.
 /*   public void CheckFinalResult(){

        if(TempPassword.equalsIgnoreCase(PasswordHolder))
        {

            Toast.makeText(MainActivity.this,"Login Successfully",Toast.LENGTH_LONG).show();

            // Going to Dashboard activity after login success message.
            Intent intent = new Intent(MainActivity.this, Home.class);

            // Sending Email to Dashboard Activity using intent.
            intent.putExtra(UserEmail, EmailHolder);

            startActivity(intent);


        }
        else {

            Toast.makeText(MainActivity.this,"UserName or Password is Wrong, Please Try Again.",Toast.LENGTH_LONG).show();

        }
        TempPassword = "NOT_FOUND" ;

    }*/

}
