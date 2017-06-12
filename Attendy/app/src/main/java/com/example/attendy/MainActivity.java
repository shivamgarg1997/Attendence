package com.example.attendy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

import static android.R.attr.password;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.provider.ContactsContract.CommonDataKinds.Website.URL;
import static com.example.attendy.R.id.faculty;

public class MainActivity extends AppCompatActivity {
    EditText user, userpass;
    TextView text;
    Button signin;
    private Firebase mref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        final Intent in = getIntent();
        final int login = in.getExtras().getInt("login");
        setContentView(R.layout.activity_main);
        text =(TextView) findViewById(R.id.login_id);
        System.out.println(login);
        if(login==0)
        text.setText("faculty");
        else text.setText("student");
        signin = (Button) findViewById(R.id.signin);
        user = (EditText) findViewById(R.id.rollno_id);
        userpass = (EditText) findViewById(R.id.password_id);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://attendy-5f3fe.firebaseio.com/login/";
                mref= new Firebase(url);
               // System.out.println("hello");
                mref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Map<String , String> map = dataSnapshot.getValue(Map.class);
                        if(map==null) {
                            Toast.makeText(MainActivity.this,
                                    "Enter Registered Email ID", Toast.LENGTH_SHORT).show();
                            }
                        String password = map.get(user.getText().toString());
                        //String password = map.get("password");
                        if(userpass.getText().toString().equals(password)){
                            String name = user.getText().toString();
                            String year = name.substring(0,2);
                            String branch = name.substring(2,5);


                            Toast.makeText(MainActivity.this,
                                    "Logged In", Toast.LENGTH_SHORT).show();
                            if(login==0){
                                Intent in = new Intent(MainActivity.this,Facultylogin.class);
                                in.putExtra("name" , name);
                                startActivity(in);
                            }
                            else {
                                Intent in = new Intent(MainActivity.this, Signup.class);
                                in.putExtra("name", name);
                                startActivity(in);
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(),
                                    "WRONG CREDENTIALS", Toast.LENGTH_SHORT).show();

                        }
                    }
                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });


            }
        });

    }
}
