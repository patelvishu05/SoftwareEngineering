package com.coderunners.spoofify;


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
public class AccountFragment extends Fragment
{
    private String options[] = {"Update Email", "Update Password", "Update Personal Details", "Contact Us", "Delete Account"};
    private ListView listView;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        ListView listView = view.findViewById(R.id._account_list);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, options);

        listView.setAdapter(listViewAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if(position == 4)
                {
                    Toast.makeText(getActivity(), "I'm Sorry, Dave.  I'm Afraid I Can't Do That.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "Coming Soon in a Future Update", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

}
