package com.yazeerahamed.theburgerzone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn,btnSignup;
    TextView txtslogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn= (Button) findViewById(R.id.btnsignin);
        btnSignup=(Button)findViewById(R.id.btnsignup);

        txtslogan =(TextView) findViewById(R.id.txtslogan);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signin= new Intent(MainActivity.this,Signin.class);
                startActivity(signin);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup= new Intent(MainActivity.this,SignUp.class);
                startActivity(signup);
            }
        });
    }
}
