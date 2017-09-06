package zli.todoplus;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.Map;

import zli.todoplus.database.DBScheme;
import zli.todoplus.objects.TodoManager;

/**
 * Created by yvokeller on 31.08.17.
 */

public class TodoListAdapter extends BaseAdapter implements ListAdapter {
    private Map<Integer, String> list = new LinkedHashMap<>();
    private TodoActivity context;

    public TodoListAdapter(Map<Integer, String> list, TodoActivity context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        //return list.get(pos).getId();
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cstom_list_item, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = view.findViewById(R.id.list_item_string);
        TextView listItemText2 = view.findViewById(R.id.list_item_string_2);

        String value = list.get(position);
        String[] parts = value.split(";");
        final String title = parts[0];
        final String type = parts[1];
        final String databaseId = parts[2];
        final String description = parts[3];
        listItemText.setText(title);
        listItemText2.setText(description);

        //Handle buttons and add onClickListeners
        Button editBtn = (Button) view.findViewById(R.id.btnEdit);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                if (type.equals("dateTodo")) {
                    context.manager.deleteDateTodo(databaseId);
                } else {
                    context.manager.deleteSportTodo(databaseId);
                }
                notifyDataSetChanged();
            }
        });
        return view;
    }
}