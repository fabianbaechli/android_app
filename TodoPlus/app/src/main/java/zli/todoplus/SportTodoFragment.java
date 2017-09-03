package zli.todoplus;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class SportTodoFragment extends Fragment {
    private EditText todoDescription;
    private EditText setStepCount;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sport_todo, container, false);
        // The three clickable elements in the fragment
        todoDescription = view.findViewById(R.id.todoDescription);
        todoDescription = view.findViewById(R.id.setStepCount);

        return view;
    }
}