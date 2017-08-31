package zli.todoplus;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.app.Fragment;
import android.view.MenuItem;

import java.util.ArrayList;

public class CreateTodoActivity extends AppCompatActivity {
    ArrayList<android.app.Fragment> fragments = new ArrayList<>();
    FragmentManager fragmentManager = getFragmentManager();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            removeActiveCenterFragments();

            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    displayFragment(new NormalTodoFragment());
                    return true;
                case R.id.navigation_notifications:
                    displayFragment(new SportTodoFragment());
                    return true;
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
        displayFragment(new NormalTodoFragment());
    }

    private void removeActiveCenterFragments() {
        if (fragments.size() > 0) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            for (Fragment activeFragment : fragments) {
                fragmentTransaction.remove(activeFragment);
            }
            fragments.clear();
            fragmentTransaction.commit();
        }
    }

    private void displayFragment(Fragment fragmentToDisplay) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_normal, fragmentToDisplay, "Added Fragment");
        fragments.add(fragmentToDisplay);
        fragmentTransaction.commit();
    }
}
