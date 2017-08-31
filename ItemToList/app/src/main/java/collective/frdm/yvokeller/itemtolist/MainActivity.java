package collective.frdm.yvokeller.itemtolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button btnAdd;
    EditText editTxt;
    ListView list;

    //SimpleAdapter adapter2;

    //private ArrayAdapter<String> adapter;
    //private ArrayList<String> arrayList;
    //private List<Map<String, String>> data;

    Map<Integer,String> myMap = new LinkedHashMap<>();
    MyCustomAdapter adapter = new MyCustomAdapter(myMap, this);

    int count = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Vars
        btnAdd = (Button) findViewById(R.id.btnAdd);
        list = (ListView) findViewById(R.id.list);
        editTxt = (EditText) findViewById(R.id.editText);
        //arrayList = new ArrayList<>();

        /*
        // Adapter: You need three parameters 'the context, id of the layout (it will be where the data is shown),
        // and the array that contains the data
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.two_line_list_item, arrayList);

        // Here, you set the data in your ListView
        list.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // this line adds the data of your EditText and puts in your array
                arrayList.add(editTxt.getText().toString());
                // next thing you have to do is check if your adapter has changed
                adapter.notifyDataSetChanged();
            }
        });


        //Verison TWO
        data = new ArrayList<>();

        adapter2 = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[] {"First Line", "Second Line" },
                new int[] {android.R.id.text1, android.R.id.text2 });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> item = new HashMap<>(2);
                item.put("First Line", editTxt.getText().toString());
                item.put("Second Line","Second line of text");
                data.add(item);

                adapter2.notifyDataSetChanged();
            }
        });

        list.setAdapter(adapter2);
        */

        //generate list
        //myMap.put(0, "Oben;Die Beschreibung unten");
        //myMap.put(1, "Oben 2;Die Beschreibung unten 2");

        //Assign Adapter
        list.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                String value = editTxt.getText().toString();

                myMap.put(count, value + ";Der untere Text.");
                adapter.notifyDataSetChanged();
            }
        });
    }
}
