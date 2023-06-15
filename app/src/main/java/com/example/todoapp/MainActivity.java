package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ADD_TODO = 1;

    private ListView listView;
    private ArrayAdapter<TodoItem> adapter;
    private List<TodoItem> todoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        // Populate the to-do items list
        todoItems = getTodoItems();

        adapter = new ArrayAdapter<TodoItem>(this, 0, todoItems) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.item_todo, parent, false);
                }

                TodoItem item = getItem(position);
                if (item != null) {
                    CheckBox checkbox = convertView.findViewById(R.id.checkbox);
                    TextView taskDescription = convertView.findViewById(R.id.taskDescription);

                    checkbox.setChecked(item.isCompleted());
                    taskDescription.setText(item.getDescription());
                }

                return convertView;
            }
        };

        listView.setAdapter(adapter);

        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle FAB click to open add/edit screen
                openAddEditScreen();
            }
        });
    }

    private void openAddEditScreen() {
        Intent intent = new Intent(MainActivity.this, AddEditTodoActivity.class);
        startActivityForResult(intent, REQUEST_ADD_TODO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ADD_TODO && resultCode == RESULT_OK && data != null) {
            TodoItem newTodoItem = data.getParcelableExtra("todoItem");
            if (newTodoItem != null) {
                todoItems.add(newTodoItem);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private List<TodoItem> getTodoItems() {
        List<TodoItem> items = new ArrayList<>();

        // Add custom items to the list
        items.add(new TodoItem("Add GraphQL Resolvers", false));
        items.add(new TodoItem("Watch The Flash", true));
        items.add(new TodoItem("Finish Reading Arsene Lupin", false));
        // Add more items as needed

        return items;
    }
}
