package com.coderunners.spoofify.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Alarm {

    public String name;
    public String time;
    public String date;

    public Alarm(String name, String time, String date){
        this.name = name;
        this.time = time;
        this.date = date;
    }

}
