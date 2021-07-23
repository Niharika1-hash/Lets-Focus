package com.example.letsfocus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CardView timercard, calendarcard, todocard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        timercard = (CardView) findViewById(R.id.cardtimer);
        calendarcard = (CardView) findViewById(R.id.cardcalendar);
        todocard = (CardView) findViewById(R.id.cardlist);

        timercard.setOnClickListener(this);
        calendarcard.setOnClickListener(this);
        todocard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch(v.getId())
        {
            case R.id.cardtimer : i = new Intent(MainActivity.this, Timer_Focus.class);
                startActivity(i);
                Toast.makeText(getBaseContext(), "You clicked on: Timer ", Toast.LENGTH_LONG).show();
            break;
            case R.id.cardcalendar : i = new Intent(MainActivity.this, Calendar_Focus.class);
                startActivity(i);
                Toast.makeText(getBaseContext(), "You clicked on: Calendar", Toast.LENGTH_LONG).show();
            break;
            case R.id.cardlist :i = new Intent(MainActivity.this, Todo_Focus.class);
                startActivity(i);
                Toast.makeText(getBaseContext(), "You clicked on: ToDo List", Toast.LENGTH_LONG).show();
            break;
            default:break;
        }

    }
}