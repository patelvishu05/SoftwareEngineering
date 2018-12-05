package com.coderunners.spoofify.Model;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Alarm {
    public int st;
    public String name;
    public String time;
    public String date;
    public long mili;
    public Intent intent;
    public PendingIntent pendingIntent;
    public AlarmManager manager;
    public Alarm(String name, String time, String date, int st, long mili, Intent intent, PendingIntent pendingIntent, AlarmManager manager){
        this.name = name;
        this.time = time;
        this.date = date;
        this.st = st;
        this.mili = mili;
        this.intent = intent;
        this.pendingIntent = pendingIntent;
        this.manager = manager;
    }

}
