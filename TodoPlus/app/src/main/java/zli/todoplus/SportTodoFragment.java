package zli.todoplus;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import zli.todoplus.objects.SportTodo;
import zli.todoplus.objects.TodoManager;

public class SportTodoFragment extends Fragment implements View.OnClickListener {
    private EditText todoDescription;
    private EditText stepCount;
    private FloatingActionButton createSportTodoButton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sport_todo, container, false);
        // The three clickable elements in the fragment
        todoDescription = view.findViewById(R.id.todoDescription);
        stepCount = view.findViewById(R.id.setStepCount);
        createSportTodoButton = view.findViewById(R.id.createSportTodoFab);
        createSportTodoButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.createSportTodoFab:

                if (!todoDescription.getText().toString().equals("") &&
                        !stepCount.getText().toString().equals("")) {
                    TodoManager manager = new TodoManager(getActivity());
                    manager.addTodo(new SportTodo(todoDescription.getText().toString(), "pending",
                            false, Integer.parseInt(stepCount.getText().toString())));
                    Intent changeActivity = new Intent(getActivity(), TodoActivity.class);
                    startActivity(changeActivity);
                }
        }
    }
}