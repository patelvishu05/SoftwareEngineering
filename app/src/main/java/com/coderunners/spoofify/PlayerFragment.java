package com.coderunners.spoofify;


import android.app.ProgressDialog;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.media.AudioAttributes;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
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
public class PlayerFragment extends Fragment {
    private FloatingActionButton play;
    private FloatingActionButton pause;
    private FloatingActionButton previous;
    private FloatingActionButton next;
    public MediaPlayer mediaPlayer;
    private TextView songName;
    private int resumeFlag = 0;
    private int resumePosition;
    private String streamName = "Loading...";
    private String url = "<server address>";
    private boolean prepared = false;
    private boolean started = false;

    public PlayerFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player, container, false);


        play = (FloatingActionButton) view.findViewById(R.id.play);
        pause = (FloatingActionButton) view.findViewById(R.id.pause);
        play.setEnabled(false);


        url = "http://10.100.118.102:8000/muse"; // your URL here
        //url = "https://currentstream1.publicradio.org:80/";
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //mediaPlayer.setAudioAttributes(new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA)
                //.setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                //.build());

        new PlayerTask().execute(url);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (started) {
                    started = false;
                    mediaPlayer.pause();
                } else {
                    started = true;
                    mediaPlayer.start();
                }
            }
        });

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });


        /*

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
        */

        songName = (TextView) view.findViewById(R.id.songName);
        songName.setText(streamName);
        // Inflate the layout for this fragment
        return view;
    }

    public void updateSongName(String song) {
        streamName = song;
        url = "http://10.100.118.102:8000/" + song;
    }


    class PlayerTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... strings) {

            try {
                System.out.println(strings[0]);
                mediaPlayer.setDataSource(strings[0]);
                mediaPlayer.prepare();
                prepared = true;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean aboolean) {
            super.onPostExecute(aboolean);
            //mediaPlayer.start();
            play.setEnabled(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (started) {
            mediaPlayer.pause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (started) {
            mediaPlayer.start();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (prepared) {
            mediaPlayer.release();
        }
    }

}