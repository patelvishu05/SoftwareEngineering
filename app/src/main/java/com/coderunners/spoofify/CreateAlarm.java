package com.coderunners.spoofify;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.coderunners.spoofify.Model.Alarm;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class CreateAlarm extends Fragment {
    //public CreateAlarm(){

    //}
    //public static CreateAlarm newInstance() {
    //    CreateAlarm fragment = new CreateAlarm();
    //    return fragment;
    //}
    private Alarm item;
    private String time;
    private String date;
    private Calendar c;
    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_alarm, container, false);

        Button save = view.findViewById(R.id.savealarm);
        TimePicker timePicker = view.findViewById(R.id.timepicker);
        DatePicker datePicker = view.findViewById(R.id.datepicker);
        final EditText updatename = view.findViewById(R.id.alarmname);
        c = Calendar.getInstance();

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                c.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                c.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                if(c.get(Calendar.MINUTE) < 10){
                    time = "" + c.get(Calendar.HOUR_OF_DAY) + ":0" + c.get(Calendar.MINUTE);
                }else {
                    time = "" + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE);
                }
            }
        });
        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                c.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
                c.set(Calendar.MONTH, datePicker.getMonth());
                c.set(Calendar.YEAR, datePicker.getYear());
                date = "" + c.get(Calendar.DAY_OF_MONTH) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.YEAR);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlarmManager manager = (AlarmManager) getContext().getSystemService(ALARM_SERVICE);
                String arg_name = updatename.getText().toString();
                Intent myIntent = new Intent(getContext(), AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, myIntent, 0);
                manager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

                //FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                //getFragmentManager().popBackStackImmediate();
                //fragmentTransaction.commit();

            }
        });
        return view;
    }
}
