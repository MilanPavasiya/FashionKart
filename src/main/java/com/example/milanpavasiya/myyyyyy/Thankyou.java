package com.example.jayghodasara.myyyyyy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Thankyou extends AppCompatActivity {

    Button b;
    FirebaseDatabase f;
    DatabaseReference ref, ref2, ref3, ref4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);
        b = (Button) findViewById(R.id.button);
b.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(Thankyou.this,Home.class);
        startActivity(i);
    }
});

    }
}
