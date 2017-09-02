package zli.todoplus.objects;

import java.util.Date;

/**
 * Created by yvokeller on 02.09.17.
 */

public class DateTodo extends AbstractTodo {
    //Attributes
    private Date reminderDate;
    private String type = "date";

    public DateTodo(String title, String state, Boolean priority, Date reminderDate) {
        super(title, state, priority);
        this.reminderDate = reminderDate;
    }

    //Functions


    //Getter & Setter
    public Date getReminderDate () {
        return reminderDate;
    }

    public void setReminderDate(Date reminderDate) {
        this.reminderDate = reminderDate;
    }

    public String getType() {
        return type;
    }
}

