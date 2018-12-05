package com.coderunners.spoofify;

import android.app.AlarmManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.coderunners.spoofify.Model.Alarm;

import java.util.ArrayList;

public class AdapterAlarm extends ArrayAdapter<Alarm> {
    private Switch state;
    public View customview;
    public AdapterAlarm(Context context, ArrayList<Alarm> alarms) {
        super(context, R.layout.alarm_item, alarms);
    }

    //for those strings i passed in this is how i want you to lay it out
    ////@androidx.annotation.NonNull
    ////@androidx.annotation.Nullable
    ////@androidx.annotation.NonNull

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layout = LayoutInflater.from(getContext());
        //equal to one item (our view)
        customview = layout.inflate(R.layout.alarm_item, parent, false);
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

        state = (Switch)customview.findViewById(R.id.state);
        //if(state.isChecked()){
        //    Toast.makeText(customview.getContext(), "Alarm Turned On",Toast.LENGTH_SHORT).show();
        //}
        if(getItem(position).st ==1){
            state.setChecked(true);
        }
        else{
            state.setChecked(false);
        }

        state.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(getItem(position).st == 0){//was 1
                    //state.isChecked()
                    Toast.makeText(customview.getContext(), "Alarm Turned On",Toast.LENGTH_SHORT).show();
                    getItem(position).st = 1; //was 0
                    getItem(position).manager.set(AlarmManager.RTC_WAKEUP, getItem(position).mili, getItem(position).pendingIntent);
                }
                else{
                    getItem(position).intent.putExtra("extra", "no");
                    getItem(position).st = 0; //was 1
                    getItem(position).manager.cancel(getItem(position).pendingIntent);
                    getContext().sendBroadcast(getItem(position).intent);
                    Toast.makeText(customview.getContext(), "Alarm Turned Off", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return customview;
    }
}
