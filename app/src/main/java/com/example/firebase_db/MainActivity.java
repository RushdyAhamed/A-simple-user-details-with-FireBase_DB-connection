package com.example.firebase_db;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText txtID,txtName,txtAddress,txtContact;
    Button btnSave,btnShow;
    DatabaseReference dbRef;
    Student std;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtID = (EditText)findViewById(R.id.txtid);
        txtName = (EditText)findViewById(R.id.txtname);
        txtAddress = (EditText)findViewById(R.id.txtaddress);
        txtContact = (EditText)findViewById(R.id.txtcontact);

        btnSave = findViewById(R.id.btnsave);
        btnShow = findViewById(R.id.btnshow);
        //btnSave = findViewById(R.id.btnsave);
        //btnSave = findViewById(R.id.btnsave);

        std = new Student();
        btnSave.setOnClickListener(this);
    }
    private void show()
    {
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Student").child("Std1");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    txtID.setText(dataSnapshot.child("id").getValue().toString());
                    txtName.setText(dataSnapshot.child("name").getValue().toString());
                    txtAddress.setText(dataSnapshot.child("address").getValue().toString());
                    txtContact.setText(dataSnapshot.child("conNo").getValue().toString());
                }
                else{
                    Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
    }

    private void save()
    {
        dbRef = FirebaseDatabase.getInstance().getReference().child("Student");

        try {
            if (TextUtils.isEmpty(txtID.getText().toString()))
            {
                Toast.makeText(getApplicationContext(), "Please Enter an ID", Toast.LENGTH_LONG).show();
            }
            else if (TextUtils.isEmpty(txtName.getText().toString()))
            {
                Toast.makeText(getApplicationContext(), "Please Enter a Name", Toast.LENGTH_LONG).show();
            }
            else if (TextUtils.isEmpty(txtAddress.getText().toString()))
            {
                Toast.makeText(getApplicationContext(), "Please Enter an Address", Toast.LENGTH_LONG).show();
            }
            else
            {
                std.setID(txtID.getText().toString());
                std.setName(txtName.getText().toString());
                std.setAddress(txtAddress.getText().toString());
                std.setConNo(Integer.parseInt(txtContact.getText().toString().trim()));

                //dbRef.push().setValue(std);

                dbRef.child("Std1").setValue(std);
                clear();
                Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_LONG).show();

            }
        }
        catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Please Contact Number", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnsave:save();
                break;

            case R.id.btnshow:show();
                break;
        }
    }

    private void clear(){

        txtID.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");

    }
}
