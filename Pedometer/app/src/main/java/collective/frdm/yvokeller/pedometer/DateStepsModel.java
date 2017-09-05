package collective.frdm.yvokeller.pedometer;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by yvokeller on 05.09.17.
 */

class DateStepsModel {

    public String mDate;
    public int mStepCount;

}

private StepsDBHelper mStepsDBHelper;
private ArrayList<DateStepsModel> mStepCountList;

public void getDataForList() {
    mStepsDBHelper = new StepsDBHelper(this.getApplicationContext());
    mStepCountList = mStepsDBHelper.readStepsEntries();
q}

class ListAdapter extends BaseAdapter {

    private TextView mDateStepCountText;

    @Override
    public int getCount() {

        return mStepCountList.size();
    }

    @Override
    public Object getItem(int position) {

        return mStepCountList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){

            convertView = getLayoutInflater().inflate(R.layout.list_rows, parent, false);
        }

        mDateStepCountText = (TextView) convertView.findViewById(R.id.sensor_name);
        mDateStepCountText.setText(mStepCountList.get(position).mDate + " - Total Steps: " + String.valueOf(mStepCountList.get(position).mStepCount));

        return convertView;
    }
}