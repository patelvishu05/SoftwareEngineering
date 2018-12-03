package com.coderunners.spoofify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.coderunners.spoofify.Model.Alarm;

import java.util.ArrayList;

public class AdapterAlarm extends ArrayAdapter<Alarm> {
    public AdapterAlarm(Context context, ArrayList<Alarm> alarms) {
        super(context, R.layout.alarm_item, alarms);
    }

    //for those strings i passed in this is how i want you to lay it out
    ////@androidx.annotation.NonNull
    ////@androidx.annotation.Nullable
    ////@androidx.annotation.NonNull

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layout = LayoutInflater.from(getContext());
        //equal to one item (our view)
        View customview = layout.inflate(R.layout.alarm_item, parent, false);
        //get everything in item
        String name = getItem(position).name;
        String time = getItem(position).time;
        String date = getItem(position).date;

        TextView updatename = (TextView)customview.findViewById(R.id.name);
        TextView updatetime = (TextView)customview.findViewById(R.id.time);
        TextView updatedate = (TextView)customview.findViewById(R.id.date);

        updatename.setText(name);
        updatetime.setText(time);
        updatedate.setText(date);

        return customview;
    }
}
