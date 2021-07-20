package com.example.lets_focus;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Fragment2 extends Fragment{
    DbHelper dbHelper;
    ArrayAdapter<String> mAdapter;
    ListView lstTask;
    Context thiscontext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment2_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dbHelper = new DbHelper(getContext());
        lstTask = (ListView)getView().findViewById(R.id.lstTask);
        loadTaskList();
    }

    private void loadTaskList(){
        ArrayList<String> taskList = dbHelper.getTaskList();
        if(mAdapter==null){
            mAdapter = new ArrayAdapter<String>(requireContext(),R.layout.row, R.id.task_title, taskList);
            lstTask.setAdapter(mAdapter);
        }
        else{
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        Drawable icon = menu.getItem(0).getIcon();
        icon.mutate();
        //icon.setColorFilter(ContextCompat.getColor(requireActivity(),R.color.white, PorterDuff.Mode.SRC_IN));
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                final EditText taskEditText = new EditText(requireContext());
                AlertDialog dialog = new AlertDialog.Builder(requireContext())
                        .setTitle("Add New Task")
                        .setMessage("What do you want to do next")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                dbHelper.insertNewTask(task);
                                loadTaskList();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();

                dialog.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void deleteTask(View view){
        View parent = (View)view.getParent();
        TextView taskTextView = (TextView) view.findViewById(R.id.task_title);
        String task = String.valueOf(taskTextView.getText());
        dbHelper.deleteTask(task);
        loadTaskList();
    }
}
