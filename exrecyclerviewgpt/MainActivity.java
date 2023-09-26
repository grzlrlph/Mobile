package com.example.exrecyclerviewgpt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTask;
    private Button buttonAdd;
    private RecyclerView recyclerViewTasks;
    private TaskAdapter taskAdapter;
    private List<Task> taskList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTask = findViewById(R.id.editTextTask);
        buttonAdd = findViewById(R.id.buttonAdd);
        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);

        taskAdapter = new TaskAdapter(taskList);
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTasks.setAdapter(taskAdapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = editTextTask.getText().toString().trim();
                if (!taskName.isEmpty()) {
                    Task task = new Task(taskName);
                    taskList.add(task);
                    taskAdapter.notifyDataSetChanged();
                    editTextTask.getText().clear();
                }
            }
        });
    }
}