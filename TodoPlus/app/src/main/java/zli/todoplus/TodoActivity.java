package zli.todoplus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import zli.todoplus.objects.TodoManager;

public class TodoActivity extends AppCompatActivity {
    Map<Integer, String> myMap = new LinkedHashMap<>();
    TodoListAdapter adapter = new TodoListAdapter(myMap, this);
    TodoManager manager = new TodoManager(this);
    ListView list;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Your Todos");
        final Intent changeActivity = new Intent(this, CreateTodoActivity.class);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(changeActivity);
            }
        });
        loadTodo();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todo, menu);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadTodo();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadTodo() {
        //Setup List
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

        //Load Data
        Map dataMap = manager.returnData();
        myMap.clear();
        Iterator it = dataMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();

            //Add Value to List
            myMap.put(Integer.parseInt(pair.getKey().toString()), pair.getValue().toString());
            System.out.println(pair.getKey().toString());
            it.remove(); // avoids a ConcurrentModificationException
        }
        adapter.notifyDataSetChanged();
    }
}
