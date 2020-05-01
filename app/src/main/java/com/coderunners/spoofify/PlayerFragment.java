package com.coderunners.spoofify;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coderunners.spoofify.Model.SingleMediaPlayer;
import com.coderunners.spoofify.Model.Stream;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerFragment extends Fragment
{
    private FloatingActionButton play;
    private FloatingActionButton pause;

    private SingleMediaPlayer mp;
    private TextView songName;
    private Stream stream;
    private ImageView albumArt;

    public PlayerFragment()
    {
        mp = SingleMediaPlayer.getInstance();
        stream = Stream.getStreamInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_player, container, false);
        if(stream.hasUrl())
        {
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try
            {
                mp.setDataSource(stream.getURL());
                mp.prepareAsync();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });


        play = (FloatingActionButton) view.findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mp.isPlaying())
                    mp.start();
            }
        });

        pause = (FloatingActionButton) view.findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (mp.isPlaying())
                {
                    mp.pause();
                }
            }
        });

        songName = (TextView) view.findViewById(R.id.songName);
        songName.setText(stream.getStreamName());

        albumArt = (ImageView) view.findViewById(R.id.albumArt);
        albumArt.setImageResource(stream.getAlbumArt());
        // Inflate the layout for this fragment
        return view;
    }

    public void updateSongName(String streamName, String streamExt)
    {
        if (mp.isPlaying())
        {
            mp.stop();
        }

        mp.reset();
        stream.setStreamName(streamName);
        stream.setURL(streamExt);

    }

}
