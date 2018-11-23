package com.coderunners.spoofify;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.ProtocolException;

public class MusicPlayer extends AppCompatActivity {

    private TextView mTextMessage;
    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton play;
    private FloatingActionButton pause;
    private FloatingActionButton previous;
    private FloatingActionButton next;
    private TextView songName;
    private ProgressBar progressBar;

    public void play(View v)
    {

    }

    public void pause(View v)
    {

    }

    public void  previous(View v)
    {

    }

    public void next(View v)
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.music_navigation);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.music_navigation);

        play = (FloatingActionButton) findViewById(R.id.play);
        pause = (FloatingActionButton) findViewById(R.id.pause);
        previous = (FloatingActionButton) findViewById(R.id.previous);
        next = (FloatingActionButton) findViewById(R.id.next);
        songName = (TextView) findViewById(R.id.songName);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                Intent intent;
                switch(menuItem.getItemId())
                {
                    case R.id.navigation_account:
                        setTitle("Account");
                        intent = new Intent(MusicPlayer.this,AccountActivity.class);
                        startActivity(intent);
                        return true;

                    case R.id.navigation_home:
                        intent = new Intent(MusicPlayer.this,LoginActivity.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });
    }

}
