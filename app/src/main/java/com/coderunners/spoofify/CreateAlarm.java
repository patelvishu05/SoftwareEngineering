package com.coderunners.spoofify;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CreateAlarm extends Fragment {
    //public CreateAlarm(){

    //}
    //public static CreateAlarm newInstance() {
    //    CreateAlarm fragment = new CreateAlarm();
    //    return fragment;
    //}
    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_alarm, container, false);
        return view;
    }
}
