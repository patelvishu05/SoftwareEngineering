package com.coderunners.spoofify.Model;
import android.media.MediaPlayer;

public class SingleMediaPlayer extends MediaPlayer
{
    private static SingleMediaPlayer instance;

    private SingleMediaPlayer()
    {

    }

    public static SingleMediaPlayer getInstance()
    {
        if(instance == null)
        {
            return instance = new SingleMediaPlayer();
        }
        else
        {
            return instance;
        }
    }
}
