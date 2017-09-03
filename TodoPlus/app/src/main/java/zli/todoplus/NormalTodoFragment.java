package zli.todoplus;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.ToggleButton;

public class NormalTodoFragment extends Fragment {
    private View.OnClickListener handleDateToggle = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final Switch dateToggle = getView().findViewById(R.id.remindMeOnADayToggle);
            dateToggle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println(dateToggle.isChecked());
                }
            });
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*
        final Switch dateToggle = getView().findViewById(R.id.priorityTodoToggle);
        dateToggle.setOnClickListener(handleDateToggle);
        */
        return inflater.inflate(R.layout.fragment_normal, null);
    }
    /*
    @Override
    public void onHiddenChanged(boolean hidden) {
        System.out.println("hello");
        super.onHiddenChanged(hidden);
        if (!hidden) {
            System.out.println("skeeet");
            final Switch dateToggle = getView().findViewById(R.id.remindMeOnADayToggle);

            dateToggle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println(dateToggle.isChecked());
                }
            });
        }
    }
    */
}