package zli.todoplus;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

public class NormalTodoFragment extends Fragment implements View.OnClickListener{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_normal, container, false);
        // The three clickable elements in the fragment
        Switch reminderToggle = view.findViewById(R.id.remindMeOnADayToggle);
        Switch priorityToggle = view.findViewById(R.id.priorityTodoToggle);
        Button setReminderButton = view.findViewById(R.id.setReminderButton);

        // Sets the click listener for the elements, which calls onClick in this class
        priorityToggle.setOnClickListener(this);
        reminderToggle.setOnClickListener(this);
        setReminderButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // Reminder Toggle event
            case R.id.remindMeOnADayToggle:
                System.out.println("reminder toggle");
                break;
            // Priority Toggle event
            case R.id.priorityTodoToggle:
                System.out.println("priority toggle");
                break;
        }
    }
}