package zli.todoplus;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NormalTodoFragment extends Fragment implements View.OnClickListener {
    private boolean isPriorityEntry = false;
    private boolean reminderDateSet = false;
    private Switch priorityToggle;
    private Switch reminderToggle;
    private Button setReminderButton;
    private EditText reminderInfo;
    private
    Calendar myCalendar = Calendar.getInstance();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_normal, container, false);
        // The three clickable elements in the fragment
        priorityToggle = view.findViewById(R.id.priorityTodoToggle);
        reminderToggle = view.findViewById(R.id.remindMeOnADayToggle);
        setReminderButton = view.findViewById(R.id.setReminderButton);
        reminderInfo = view.findViewById(R.id.reminderInfo);

        // Sets the click listener for the elements, which calls onClick in this class
        priorityToggle.setOnClickListener(this);
        reminderToggle.setOnClickListener(this);
        setReminderButton.setEnabled(false);
        setReminderButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Reminder Toggle event which makes the setReminderButton clickable
            case R.id.remindMeOnADayToggle:
                if (reminderToggle.isChecked()) {
                    setReminderButton.setEnabled(true);
                } else {
                    reminderInfo.setVisibility(View.INVISIBLE);
                    setReminderButton.setEnabled(false);
                    reminderDateSet = false;
                }
                break;
            // Priority Toggle event which sets the isPriorityEntry var
            case R.id.priorityTodoToggle:
                isPriorityEntry = priorityToggle.isChecked();
                break;
            // Set Reminder Button toggle which displays the DatePicker
            case R.id.setReminderButton:
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    private void updateLabel() {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.GERMAN);
        Date today = myCalendar.getTime();
        String setDate = formatter.format(today);
        reminderInfo.setVisibility(View.VISIBLE);
        reminderInfo.setText("Set date: " + setDate);
        reminderDateSet = true;
    }
}