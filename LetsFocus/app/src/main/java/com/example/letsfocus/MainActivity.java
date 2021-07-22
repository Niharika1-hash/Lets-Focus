package com.example.letsfocus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.listview1);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        adapter.add("Timer");
        adapter.add("Calendar");
        adapter.add("To-Do list");

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String item = (String) adapter.getItem(position);
        Toast.makeText(getBaseContext(), "You clicked on: " + item, Toast.LENGTH_LONG).show();

        if(position == 0)
        {
            Intent intent = new Intent(MainActivity.this, Timer_Focus.class);
            startActivity(intent);
        }
        if(position == 1)
        {
            Intent intent = new Intent(MainActivity.this, Calendar_Focus.class);
            startActivity(intent);
        }
        if(position == 2)
        {
            Intent intent = new Intent(MainActivity.this, Todo_Focus.class);
            startActivity(intent);
        }
    }
}