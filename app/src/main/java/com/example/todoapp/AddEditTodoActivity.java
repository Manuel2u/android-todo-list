// AddEditTodoActivity.java
package com.example.todoapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddEditTodoActivity extends AppCompatActivity {

    private EditText etTaskDescription;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        etTaskDescription = findViewById(R.id.editTextDescription);
        btnSave = findViewById(R.id.buttonSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskDescription = etTaskDescription.getText().toString();

                TodoItem newTodoItem = new TodoItem(taskDescription, false);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("todoItem", newTodoItem);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
