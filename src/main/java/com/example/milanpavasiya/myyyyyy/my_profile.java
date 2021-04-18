package com.example.jayghodasara.myyyyyy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Jay Ghodasara on 10/9/2017.
 */
public class my_profile extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartactivity);
    Intent intent=new Intent(Intent.ACTION_SEND);
    intent.setType("text/plain");
    intent.putExtra(Intent.EXTRA_EMAIL,"nadsmaulvi@gmail.com");
    intent.putExtra(Intent.EXTRA_SUBJECT,"Feedback");

    if(intent.resolveActivity(getPackageManager())!=null){
        startActivity(intent);
    }
    }
}
