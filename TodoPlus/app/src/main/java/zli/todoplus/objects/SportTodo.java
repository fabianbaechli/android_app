package zli.todoplus.objects;

import java.util.Date;

/**
 * Created by yvokeller on 02.09.17.
 */

public class SportTodo extends AbstractTodo{
    //Attributes
    private String type = "sport";
    private Integer stepGoal;
    private Integer stepsDone;
    private Integer timeUsed;

    public SportTodo(String title, String state, Boolean priority, int stepGoal) {
        super(title, state, priority);

        this.stepGoal = stepGoal;
    }

    //Functions


    //Getter & Setter
    public String getType() {
        return type;
    }

    public Integer getStepGoal() {
        return stepGoal;
    }

    public void setStepGoal(Integer stepGoal) {
        this.stepGoal = stepGoal;
    }

    public Integer getStepsDone() {
        return stepsDone;
    }

    public void setStepsDone(Integer stepsDone) {
        this.stepsDone = stepsDone;
    }

    public Integer getTimeUsed() {
        return timeUsed;
    }

    public void setTimeUsed(Integer timeUsed) {
        this.timeUsed = timeUsed;
    }
}
