package zli.todoplus;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class CreateTodoActivity extends AppCompatActivity {

    private TextView mTextMessage;
    FragmentManager fragmentManager = getFragmentManager();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    NormalTodoFragment hello = new NormalTodoFragment();
                    fragmentTransaction.add(R.id.fragment_normal, hello, "HELLO");
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_notifications:
                    SportTodoFragment sportTodoFragment = new SportTodoFragment();
                    fragmentTransaction.add(R.id.fragment_normal, sportTodoFragment, "HELLO");
                    fragmentTransaction.commit();
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_todo);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        NormalTodoFragment hello = new NormalTodoFragment();
        fragmentTransaction.add(R.id.fragment_normal, hello, "HELLO");
        fragmentTransaction.commit();
    }
}
