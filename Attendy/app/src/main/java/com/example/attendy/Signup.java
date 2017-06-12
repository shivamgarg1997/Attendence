package com.example.attendy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.System.exit;


public class Signup extends AppCompatActivity  {
    Button submit1;
    public int seconds;
    public int minutes ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent in = getIntent();

        String name = in.getExtras().getString("name");
        String year = name.substring(0,2);
        String branch = name.substring(2,5);
        setContentView(R.layout.activity_signup);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinnercourse = (Spinner) findViewById(R.id.spinner_2);
       /* ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.lecture_hall, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/
        String[] items = new String[]{"1", "2", "3" , "4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
        if(year.equals("15")){
            if(branch.equals("ucs")){
                items = new String[]{"oop", "2", "3" , "4"};
                 adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
                spinnercourse.setAdapter(adapter);
            }
            else if(branch.equals("uce")){
                items = new String[]{"1", "dbms", "3" , "4"};
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
                spinnercourse.setAdapter(adapter);
            }
            else if(branch.equals("cce")){
                items = new String[]{"1", "2", "daa" , "4"};
                 adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
                spinnercourse.setAdapter(adapter);
            }
            else if(branch.equals("mme")){
                items = new String[]{"1", "2", "3" , "ie"};
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
                spinnercourse.setAdapter(adapter);
            }
        }
        submit1 = (Button) findViewById(R.id.submit);
        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Signup.this, "PRESENT", Toast.LENGTH_SHORT).show();
                submit1.setEnabled(false);
                minutes=1;
                seconds = 10;


                Timer t = new Timer();

                Timer buttonTimer = new Timer();
                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {


                            @Override
                            public void run() {


                                if(minutes>=0) {

                                    TextView tv = (TextView) findViewById(R.id.main_timer_text);
                                    tv.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));
                                    seconds -= 1;
                                    System.out.println(seconds+":"+minutes);
                                }

                               if(seconds == 0)
                                {
                                    TextView tv = (TextView) findViewById(R.id.main_timer_text);
                                   // tv.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));
                                    seconds=60;
                                    minutes=minutes-1;
                                    if(minutes==-1){
                                        tv.setText("ha");

                                    }
                                    tv.setText("");
                                    return;
                                }

                            }
                        });
                    }
                },0, 1000);

                buttonTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                submit1.setEnabled(true);
                                return;
                                /*TextView tv = (TextView) findViewById(R.id.main_timer_text);
                                tv.setText("ha");*/

                            }
                        });
                    }
                }, 10000);


               // submit1.setBackgroundColor(getResources().getColor(R.color.backline));
            }
        });





         /*ArrayAdapter<CharSequence> courseadapter = ArrayAdapter.createFromResource(this,
                R.array.courses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnercourse.setAdapter(courseadapter);*/
    }
    /*@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }*/
   /* public View submit(final View view)
    {
        view.setClickable(false);
        submit1.setHint("jelll");

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                // this code will be executed after 2 seconds
                System.out.println("hello");
                view.setClickable(true);

                //submit1.setBackgroundColor(Color.WHITE);
               // Button submit1;
               // submit1.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }, 10000);
        System.out.println("hello2");
        return view;
    }*/
}
