package zli.todoplus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.ToggleButton;

public class AddTodoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        setTitle("Add Todo");
        final Switch sportTodo = (Switch) findViewById(R.id.sportTodoToggle);
        final Switch remindMe = (Switch) findViewById(R.id.remindmeToggle);
        final Switch priorityTodo = (Switch) findViewById(R.id.priorityTodoToggle);
        sportTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sportTodo.isChecked()) {
                    remindMe.setClickable(false);
                    priorityTodo.setClickable(false);
                } else {
                    remindMe.setClickable(true);
                    priorityTodo.setClickable(true);
                }

            }
        });

        remindMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        priorityTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
