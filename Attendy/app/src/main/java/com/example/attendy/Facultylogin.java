package com.example.attendy;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.client.Firebase;

import static android.R.attr.key;
import static com.example.attendy.R.id.course;
import static com.example.attendy.R.id.signin;
import static com.example.attendy.R.id.spinner;

public class Facultylogin extends AppCompatActivity {
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }
    EditText key;
    Spinner lecture,course;
    Button submit;
    private Firebase mref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_facultylogin);
        Intent in = getIntent();

        String name = in.getExtras().getString("name");
        String year = name.substring(0,2);
        String branch = name.substring(2,5);
        mref  = new Firebase("https://attendy-5f3fe.firebaseio.com/key");
        submit = (Button) findViewById(R.id.submit);
        lecture = (Spinner) findViewById(spinner);
        course = (Spinner) findViewById(R.id.spinner_2);
        key = (EditText)findViewById(R.id.key_id);
        String[] items = new String[]{"1", "2", "3" , "4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        lecture.setAdapter(adapter);
        if(year.equals("15")){
            if(branch.equals("ucs")){
                items = new String[]{"oop", "2", "3" , "4"};
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
                course.setAdapter(adapter);
            }
            else if(branch.equals("uce")){
                items = new String[]{"1", "dbms", "3" , "4"};
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
                course.setAdapter(adapter);
            }
            else if(branch.equals("cce")){
                items = new String[]{"1", "2", "daa" , "4"};
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
                course.setAdapter(adapter);
            }
            else if(branch.equals("mme")){
                items = new String[]{"1", "2", "3" , "ie"};
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
                course.setAdapter(adapter);
            }
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                String value = key.getText().toString();
                String lect = lecture.getSelectedItem().toString();
                String cour = course.getSelectedItem().toString();
                String date = now();
                String finalkey = value + lect + cour + date;
                Firebase childref = mref.child("key");
                childref.push().setValue(finalkey);


            }
        });
    }
}
