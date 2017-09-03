package zli.todoplus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import zli.todoplus.objects.DateTodo;
import zli.todoplus.objects.SportTodo;
import zli.todoplus.objects.Todo;
import zli.todoplus.objects.TodoManager;

public class TodoActivity extends AppCompatActivity {
    Map<Integer,String> myMap = new LinkedHashMap<>();
    TodoListAdapter adapter = new TodoListAdapter(myMap, this);

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

    public void loadTodo(){
        TodoManager manager = new TodoManager();
        //TextView textView = (TextView) findViewById(R.id.textView);

        //Put Into Database
        manager.addTodo(new DateTodo("App Programmieren", "pending", true, new Date()), getApplicationContext());
        manager.addTodo(new SportTodo("Laufen", "pending", true, 150), getApplicationContext());

        //Setup List
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

        //Load Data
        Map<Integer,String> dataMap = manager.returnData();

        Iterator it = dataMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();

            //Add Value to List
            myMap.put(Integer.parseInt(pair.getKey().toString()), pair.getValue().toString());

            it.remove(); // avoids a ConcurrentModificationException
        }

        adapter.notifyDataSetChanged();

    }
}
