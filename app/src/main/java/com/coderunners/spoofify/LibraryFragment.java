package com.coderunners.spoofify;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class LibraryFragment extends Fragment
{
    // Temporary test values
    private String items[] = new String[] {"Apple", "Orange", "Banana", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M"};
    private OnFragmentInteractionListener fListener;

    public LibraryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_library, container, false);

        ListView listView = view.findViewById(R.id.songlist);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);

        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                //Toast.makeText(getActivity(), items[position], Toast.LENGTH_SHORT).show();
                // Loads the PlayerFragment view
                fListener = (OnFragmentInteractionListener) getActivity();
                fListener.changeFragment(3);
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
