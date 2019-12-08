package com.yazeerahamed.theburgerzone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.yazeerahamed.theburgerzone.Model.user;

public class SignUp extends AppCompatActivity {

    MaterialEditText editphone,editname,editpassword;
    Button btnsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editphone = (MaterialEditText)findViewById(R.id.editphone);
        editname = (MaterialEditText)findViewById(R.id.editname);
        editpassword = (MaterialEditText)findViewById(R.id.editpassword);
        btnsignup=(Button)findViewById(R.id.btnsignup);


        FirebaseDatabase database= FirebaseDatabase.getInstance();
        final DatabaseReference table_user= database.getReference("user");

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                mDialog.setMessage("Please Wait");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(editphone.getText().toString()).exists()) {
                            mDialog.dismiss();

                            Toast.makeText(SignUp.this, "Phone Number already registered ! !", Toast.LENGTH_SHORT).show();
                        } else {
                            mDialog.dismiss();
                            user user = new user(editname.getText().toString(), editpassword.getText().toString());
                            table_user.child(editphone.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this, "Signup Successfull ! !", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
