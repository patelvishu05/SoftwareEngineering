package com.coderunners.spoofify;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmFragment extends Fragment {

    TimePicker alarmTime;
    Button setAlarm;
    TextView tvcurrentalarm;
    int minute;
    int hour;
    String alarmtext;
    public AlarmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_alarm, container, false);
        alarmTime = rootView.findViewById(R.id.timePicker);
        setAlarm = rootView.findViewById(R.id.alarmSetButton);

        tvcurrentalarm = rootView.findViewById(R.id.currentAlarmText);

        setAlarm.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
               hour = alarmTime.getHour();
               minute = alarmTime.getMinute();
               alarmtext  = "Current Alarm: "+hour+":" + minute;
                tvcurrentalarm.setText(alarmtext);

            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }

}
