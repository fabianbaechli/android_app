package zli.todoplus.objects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by yvokeller on 02.09.17.
 */

public class DateTodo extends AbstractTodo {
    //Attributes
    private String reminderDate;
    private String type = "date";

    public DateTodo(String title, String state, Boolean priority, Date reminderDate) {
        super(title, state, priority);

        this.reminderDate = formatReminderDate(reminderDate);
    }

    //Functions
    public String formatReminderDate(Date reminderDate){
        DateFormat df = new SimpleDateFormat("dd.MM.YYYY mm:ss");
        String newReminderDate = df.format(reminderDate);
        return newReminderDate;
    }

    //Getter & Setter
    public String getType() {
        return type;
    }

    public String getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(String reminderDate) {
        this.reminderDate = reminderDate;
    }
}

