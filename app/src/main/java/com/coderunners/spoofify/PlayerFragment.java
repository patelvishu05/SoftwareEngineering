package com.coderunners.spoofify;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerFragment extends Fragment
{
    private FloatingActionButton play;
    private FloatingActionButton pause;
//    private FloatingActionButton previous;
//    private FloatingActionButton next;
    private static MediaPlayer mp;
    private TextView songName;
    private String URL;
    private Boolean hasUrl;
    private int resumeFlag=0;
    private int resumePosition;
    private String streamName = "Default";

    public PlayerFragment()
    {
        mp = new MediaPlayer();
        hasUrl = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_player, container, false);
        if(hasUrl) {
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mp.setDataSource(URL);
//                mp.setDataSource("http://10.100.118.102:8000/rhcp");
                mp.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp)
            {
                mp.start();
            }
        });


        play = (FloatingActionButton) view.findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if(!mp.isPlaying())
                    mp.start();
            }
        });

        pause = (FloatingActionButton) view.findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (mp.isPlaying() && resumeFlag == 0)
                {
                    mp.pause();
                    resumePosition = mp.getCurrentPosition();
                }
                if((!mp.isPlaying()) && resumeFlag == 1)
                {
                    mp.seekTo(resumePosition);
                    mp.start();
                }
                if(resumeFlag == 0) resumeFlag = 1;
                else resumeFlag = 0;
            }
        });


        songName = (TextView) view.findViewById(R.id.songName);
        songName.setText(streamName);

        return view;
    }

    public void updateSongName(String song)
    {
        //String url = "http://10.100.118.102:8000/rhcp";
        if (mp.isPlaying())
            mp.stop();
        mp.reset();
        streamName = song;
        URL = "";
        URL = "http://10.100.118.102:8000/" + song;
        hasUrl = true;
    }

}
