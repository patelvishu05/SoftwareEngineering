package com.coderunners.spoofify;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class RingtoneService extends Service {
    //ADD TO MANIFEST
    //<service android:name=".RingtoneService"
    //android:enabled="true">
    //</service>
    private MediaPlayer mediaPlayer;
    private int startId;
    private boolean isRunning;
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        //TODO: fetch the extra string value
        String state = intent.getExtras().getString("extra");
        //either "on" or "off"
        assert state != null; //deals with null pointer
        switch (state) {
            case "on":
                startId = 1;
                break;
            case "off":
                startId = 0;
                break;
            default:
                startId = 0; //do nothing default
                break;
        }
        //TODO: if else statements
        //if no music playing, and "on" is clicked: music should start playing
        if(!this.isRunning && startId == 1){
            mediaPlayer = MediaPlayer.create(this, R.raw.chainsmokers);
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
            this.isRunning = true;
            this.startId = 0;
            /*Use this after successfully storing alarm list
            //TODO: make a notification
            NotificationManager notificationManager = (NotificationManager)
                    getSystemService(NOTIFICATION_SERVICE);
            // what pop ups: set up an intent that goes to the Main Activity
            Intent intent1 = new Intent(this.getApplicationContext(), AlarmFragment.class);
            //set up a pending intent
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent1,0);
            //make the notification parameters
            //title, content, intent, dismiss true
            Notification notification = new Notification.Builder(this)
                    .setContentTitle("Your alarm is going off!")
                    .setContentText("Click me!")
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build();
            //set up the notification call command
            //added @target api above this method
            notificationManager.notify(0, notification);
            */
        }
        //if no music playing, and "off" is clicked:  music stop playing
        else if(this.isRunning && startId == 0){
            //stop media player
            mediaPlayer.stop();
            mediaPlayer.reset();
            this.isRunning = false;
            this.startId = 0;
        }
        //if user presses random buttons (no music playing and click "off"): nothing
        else if(!this.isRunning && startId == 0){
            this.isRunning = false;
            this.startId = 0;
        }
        //if music is playing and click "on": nothing
        else if(this.isRunning && startId == 1){
            this.isRunning = true;
            this.startId = 1;
        }
        //can't think of anything else, catches odd event
        else{
            Log.e("else", "somehow we got here");
        }
        //media player used to be here
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Tell the user we stopped.
        //Toast.makeText(this, "on destroyed called", Toast.LENGTH_SHORT).show();
        super.onDestroy();
        this.isRunning = false;
    }

}
