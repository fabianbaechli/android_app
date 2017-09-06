package zli.todoplus.objects;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yvokeller on 02.09.17.
 */

public abstract class AbstractTodo implements Todo{
    //Attributes
    private int id;
    private String title;
    private Date creationDate;
    private String state;
    private Boolean priority;

    public AbstractTodo(String title, String state, Boolean priority){
        this.id = 1;

        Calendar myCalendar = Calendar.getInstance();
        this.creationDate = myCalendar.getTime();

        this.title = title;
        this.state = state;
        this.priority = priority;
    }

    //Functions
    @Override
    public void terminateTodo() {

    }

    //Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getPriority() {
        return priority;
    }

    public void setPriority(Boolean priority) {
        this.priority = priority;
    }
}
