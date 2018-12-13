package com.coderunners.spoofify;


import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.coderunners.spoofify.Model.Alarm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.MODE_PRIVATE;
import static android.support.v4.content.ContextCompat.getSystemService;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmFragment extends Fragment{
    //private EditText editText;
    private int index;
    private String amPm;
    private Calendar calendar;
    private Button addbutton;
    private Button delete;
    private Button cancel;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private String str;
    //private String alarms[] = new String[] {"alarm 1", "alarm 2", "alarm 3"};
    //private ArrayList<Alarm> alarms = new ArrayList<>();
    private ArrayList<Alarm> alarms;
    //Alarm single = new Alarm("item1", "04:32", "12-05-67");
    private OnFragmentInteractionListener fListener;
    //List<Alarm> alarms = new ArrayList<Alarm>();
    private AdapterAlarm alarmAdapter;
    public AlarmFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //alarms.add(new Alarm("test", "08:09AM", "12-05-2018"));
        loadData(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_alarm, container, false);
        alarmAdapter = new AdapterAlarm(this.getActivity(),alarms);
        ListView listView = view.findViewById(R.id.alarmlist);
        listView.setAdapter(alarmAdapter);
        delete = view.findViewById(R.id.delete2);
        cancel = view.findViewById(R.id.cancel2);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                deleteClick(i);

            }
        });

        addbutton = view.findViewById(R.id.addAlarm);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createAlarm();

            }
        });
        return view;
    }

    private void createAlarm(){

        int currentHour;
        int currentMinute;


        //set current time
        calendar = Calendar.getInstance();
        currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        currentMinute = calendar.get(Calendar.MINUTE);

        //set current date
        calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);


        timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minutes);
                if (hourOfDay >= 12) {
                    if(hourOfDay > 12){
                        hourOfDay = hourOfDay-12;}
                    amPm = "PM";
                } else {
                    if(hourOfDay == 0){
                        hourOfDay = 12;
                    }
                    amPm = "AM";
                }
                str = String.format("%02d:%02d", hourOfDay, minutes) + amPm;

                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, month);
                                calendar.set(Calendar.DAY_OF_MONTH, day);
                                String date = String.format("%02d-%02d-%04d", month+1, day, year);

                                //INTENT
                                //PENDING INTENT
                                //ALARM MANAGER
                                Intent intent = new Intent(getActivity(), AlarmReceiver.class);
                                //TODO: put in string into intent, tells the clock that you pressed on
                                intent.putExtra("extra", "on");
                                //PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0,intent, PendingIntent.FLAG_UPDATE_CURRENT);
                                //AlarmManager manager = (AlarmManager)getActivity().getSystemService(ALARM_SERVICE);
                                //manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pendingIntent);
                                //Alarm a = new Alarm("Alarm:", str, date, 1, calendar.getTimeInMillis(), intent, pendingIntent, manager);

                                //PendingIntent pending = PendingIntent.getBroadcast(getActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                                PendingIntent pending = PendingIntent.getBroadcast(getActivity(), alarms.size()-1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                                AlarmManager manager = (AlarmManager)getActivity().getSystemService(ALARM_SERVICE);
                                manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending);
                                Alarm a = new Alarm("Alarm:", str, date, 1, calendar.getTimeInMillis());
                                alarms.add(a);
                                saveData(getActivity());
                                alarmAdapter.notifyDataSetChanged();
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();

            }
        }, currentHour, currentMinute, false);

        timePickerDialog.show();
    }


    private void deleteClick(final int i){
        addbutton.setVisibility(View.GONE);
        delete.setVisibility(View.VISIBLE);
        cancel.setVisibility(View.VISIBLE);
        cancelClick();
        index = i;
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete.setText("Here");
                //alarms.get(index).manager.cancel(alarms.get(index).pendingIntent);
                //getContext().sendBroadcast(alarms.get(index).intent);
                Intent intent = new Intent(getActivity(), AlarmReceiver.class);
                intent.putExtra("extra", "off");
                PendingIntent pending = PendingIntent.getBroadcast(getActivity(), alarms.size()-1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager manager = (AlarmManager)getContext().getSystemService(ALARM_SERVICE);
                manager.cancel(pending);
                getActivity().sendBroadcast(intent);
                alarms.remove(index);
                saveData(getActivity());
                alarmAdapter.notifyDataSetChanged();
                delete.setVisibility(View.GONE);
                cancel.setVisibility(View.GONE);
                addbutton.setVisibility(View.VISIBLE);
            }
        });
    }
    private void cancelClick(){
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete.setVisibility(View.GONE);
                cancel.setVisibility(View.GONE);
                addbutton.setVisibility(View.VISIBLE);
            }
        });

    }
    private void saveData(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared preferences", MODE_PRIVATE);
        //SharedPreferences sharedPrefrences= getSharedPreferences("shared preferences", MODE_PRIVATE)
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(alarms);
        editor.putString("alarms list", json);
        editor.apply();
    }
    private void loadData(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("alarms list", null);
        Type type = new TypeToken<ArrayList<Alarm>>(){}.getType();
        alarms = gson.fromJson(json,type);
        if (alarms == null){
            alarms = new ArrayList<>();
        }
    }
    @Override
    public void onDetach(){
        super.onDetach();
        fListener = null;

    }

}
