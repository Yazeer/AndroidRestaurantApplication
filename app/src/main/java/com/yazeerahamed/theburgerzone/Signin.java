package com.yazeerahamed.theburgerzone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.yazeerahamed.theburgerzone.Common.Common;
import com.yazeerahamed.theburgerzone.Model.user;

public class Signin extends AppCompatActivity {
 EditText editphone,editpassword;
 Button btnsignin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        editphone=(MaterialEditText)findViewById(R.id.editphone);
        editpassword=(MaterialEditText)findViewById(R.id.editpassword);

        btnsignin=(Button)findViewById(R.id.btnsignin);

// Initialise firebase


        FirebaseDatabase database= FirebaseDatabase.getInstance();
        final DatabaseReference table_user= database.getReference("user");

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(Signin.this);
                mDialog.setMessage("Please Wait");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        //check if user exists
                        if (dataSnapshot.child(editphone.getText().toString()).exists()) {


                            // Receive user information
                            mDialog.dismiss();
                            user user = dataSnapshot.child(editphone.getText().toString()).getValue(user.class);
                            user.setPhone(editphone.getText().toString());//Set Phone
                            if (user.getPassword().equals(editpassword.getText().toString())) {
                                Intent homeintent = new Intent(Signin.this,Home.class);
                                Common.currentUser=user;
                                startActivity(homeintent);
                                finish();

                            } else {
                                Toast.makeText(Signin.this, "Sign in Failed !", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            mDialog.dismiss();
                            Toast.makeText(Signin.this, "User doesn't exist !", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
