package com.example.attendy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.example.attendy.R.id.faculty;

public class SuperActivity extends AppCompatActivity {

    Button fac, stu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super);

        fac = (Button) findViewById(R.id.faculty);
        stu = (Button) findViewById(R.id.student);
        fac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                faculty(view);
            }
        });
        stu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                student(view);
            }
        });

    }
    public void faculty (View view){
        int login = 0;
        Intent in = new Intent(SuperActivity.this,MainActivity.class);
        in.putExtra("login" , login);
        startActivity(in);
    }
    public void student (View view){
        int login = 0;
        login++;
        Intent in = new Intent(SuperActivity.this,MainActivity.class);
        in.putExtra("login" , login);
        startActivity(in);


    }
}
