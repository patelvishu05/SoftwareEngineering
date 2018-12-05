package com.coderunners.spoofify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("In reciver", "yay");

        //TODO: fetch extra string from the intent
        String getString = intent.getExtras().getString("extra");

        //create an intetn to the ringtone service
        //where we start, were we go
        Intent intent1 = new Intent(context, RingtoneService.class);
        //TODO: pass the extra string from main activity to RingtoneService
        intent1.putExtra("extra", getString);

        context.startService(intent1);
    }
}
