package zli.todoplus;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import zli.todoplus.notification.MyReceiver;
import zli.todoplus.objects.DateTodo;
import zli.todoplus.objects.TodoManager;

public class NormalTodoFragment extends Fragment implements View.OnClickListener {
    private boolean isPriorityEntry = false;
    private boolean reminderDateSet = false;
    private Switch priorityToggle;
    private Switch reminderToggle;
    private Button setReminderButton;
    private EditText reminderInfo;
    private EditText todoDescription;
    private FloatingActionButton createButton;
    Calendar myCalendar = Calendar.getInstance();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_normal, container, false);
        // The three clickable elements in the fragment
        priorityToggle = view.findViewById(R.id.priorityTodoToggle);
        reminderToggle = view.findViewById(R.id.remindMeOnADayToggle);
        setReminderButton = view.findViewById(R.id.setReminderButton);
        reminderInfo = view.findViewById(R.id.reminderInfo);
        createButton = view.findViewById(R.id.createTodoFab);
        todoDescription = view.findViewById(R.id.todoDescription);

        // Sets the click listener for the elements, which calls onClick in this class
        priorityToggle.setOnClickListener(this);
        reminderToggle.setOnClickListener(this);
        setReminderButton.setEnabled(false);
        setReminderButton.setOnClickListener(this);
        createButton.setOnClickListener(this);
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
                break;
            case R.id.createTodoFab:
                // If the tododescription is not empty
                if (!todoDescription.getText().toString().equals("")) {
                    System.out.println("created todo");

                    TodoManager manager = new TodoManager(getActivity());
                    manager.addTodo(new DateTodo(todoDescription.getText().toString(), "pending",
                            isPriorityEntry, myCalendar.getTime()));
                    Intent changeActivity = new Intent(getActivity(), TodoActivity.class);
                    startActivity(changeActivity);

                    Intent notifyIntent = new Intent(getActivity(), MyReceiver.class);
                    System.out.println("Name:" + todoDescription.getText().toString());

                    PendingIntent pendingIntent = PendingIntent.getBroadcast
                            (getActivity(), 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                    System.out.println(myCalendar.getTimeInMillis());
                    alarmManager.set(AlarmManager.RTC_WAKEUP, myCalendar.getTimeInMillis(), pendingIntent);
                }
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