package com.coderunners.spoofify;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MusicPlayer extends AppCompatActivity {

    private TextView mTextMessage;
    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton playIt;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.music_navigation);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.music_navigation);

        playIt = (FloatingActionButton) findViewById(R.id.play);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                Intent intent;
                switch(menuItem.getItemId())
                {
                    case R.id.navigation_account:
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
