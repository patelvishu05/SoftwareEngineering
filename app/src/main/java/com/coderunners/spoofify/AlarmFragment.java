package com.coderunners.spoofify;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmFragment extends Fragment {
    //private EditText editText;
    private Button addbutton;
    //private AlarmFragmentListener listener;
    private String alarms[] = new String[] {"heelo", "there", "how"};
    private OnFragmentInteractionListener flistener;
    public AlarmFragment() {
        // Required empty public constructor
    }
    //public interface AlarmFragmentListener{
    //    void onInputASent(CharSequence input);
    //}
    /*@Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_alarm, container, false);

        editText = v.findViewById(R.id.edit_text);
        okbutton = v.findViewById(R.id.ok);

        okbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get input
                CharSequence input = editText.getText();
                listener.onInputASent(input);
            }
        });
        return v;
    }
    public void updateText(CharSequence newText){
        //get data from activity into fragment
        editText.setText(newText);
    }
    //called when fragment is attached to activity
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        //if activity implements interface
        if(context instanceof AlarmFragmentListener){
            listener = (AlarmFragmentListener)context;
        }
        else{
            throw new RuntimeException(context.toString() + " must implement Fragmentlistener");
        }
    }
    @Override
    public void onDetach(){
        //dont need fragment anyomre
        super.onDetach();
        listener = null;
    }
    */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);
        addbutton = view.findViewById(R.id.addAlarm);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_create_alarm
            }
        });
        return view;
    }

    @Override

    public void onDetach()

    {
        super.onDetach();
        flistener = null;

    }

}
