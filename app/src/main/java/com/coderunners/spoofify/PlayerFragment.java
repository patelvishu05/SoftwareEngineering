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
    private FloatingActionButton previous;
    private FloatingActionButton next;
    private static MediaPlayer mp;
    private TextView songName;
    private int resumeFlag=0;
    private int resumePosition;

    public PlayerFragment() {
        // Required empty public constructor
        mp = new MediaPlayer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_player, container, false);
        String sourceURL = "https://freemusicdownloads.world/wp-content/uploads/2017/05/Justin-Bieber-Sorry-PURPOSE-The-Movement.mp3";
//        String sourceURL = "http://10.100.118.102:8000/muse";
        mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mp.setDataSource(sourceURL);
            mp.prepareAsync();



        } catch (IOException e) {
            e.printStackTrace();
        }
        play = (FloatingActionButton) view.findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying() == false)
                {

                    //mp = MediaPlayer.create(getActivity(), R.raw.jinglebells);
                    //mp = MediaPlayer.create()
                    mp.start();
                }
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

        previous = (FloatingActionButton) view.findViewById(R.id.previous);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying())
                    mp.stop();
                mp = MediaPlayer.create(getActivity(),R.raw.chainsmokers);
                mp.start();
            }
        });

        next = (FloatingActionButton) view.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying())
                    mp.stop();
                mp = MediaPlayer.create(getActivity(),R.raw.deckthehalls);
                mp.start();
            }
        });

        songName = (TextView) view.findViewById(R.id.songName);
        // Inflate the layout for this fragment
        return view;
    }

    public void updateSongName(String song)
    {
        songName.setText(song);
        songName.invalidate();
        songName.requestLayout();
    }

}
