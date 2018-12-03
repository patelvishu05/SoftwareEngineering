package com.coderunners.spoofify;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.coderunners.spoofify.Model.Alarm;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmFragment extends Fragment{
    //private EditText editText;
    private Button addbutton;
    //private AlarmFragmentListener listener;
    //private String alarms[] = new String[] {"alarm 1", "alarm 2", "alarm 3"};
    private ArrayList<Alarm> alarms = new ArrayList<>();
    //private List<Alarm> alarms = new ArrayList<>();
    //Alarm single = new Alarm("item1", "04:32", "12-05-67");
    private OnFragmentInteractionListener fListener;
    //List<Alarm> alarms = new ArrayList<Alarm>();
    private AdapterAlarm alarmAdapter;
    public AlarmFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Alarm a = new Alarm("alarm 1", "09:45", "12-05-92");
        alarms.add(a);
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);
        alarmAdapter = new AdapterAlarm(this.getActivity(),alarms);
        ListView listView = view.findViewById(R.id.alarmlist);
        listView.setAdapter(alarmAdapter);
        //ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
        //        getActivity(), android.R.layout.simple_list_item_1, alarms);
        //listView.setAdapter(listViewAdapter);
        //listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

        //    @Override
        //    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        //    {
                //Toast.makeText(getActivity(), items[position], Toast.LENGTH_SHORT).show();
                // Loads the PlayerFragment view
        //        fListener = (OnFragmentInteractionListener) getActivity();
        //        fListener.changeFragment(4, alarms[position]);
        //    }
        //});
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CreateAlarm fragment = new CreateAlarm();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.main_frame, new CreateAlarm());
                transaction.commit();
            }
        });
        */
        addbutton = view.findViewById(R.id.addAlarm);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //CreateAlarm fragment = new CreateAlarm();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.main_frame, new CreateAlarm());
                //transaction.add(R.id.main_frame, fragment);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }

    @Override

    public void onDetach()

    {
        super.onDetach();
        fListener = null;

    }

}
