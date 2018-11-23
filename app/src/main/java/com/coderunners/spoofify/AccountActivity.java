package com.coderunners.spoofify;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

public class AccountActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.accountNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                Intent intent;
                switch(menuItem.getItemId())
                {
                    case R.id.navigation_music:
                        intent = new Intent(AccountActivity.this,MusicPlayer.class);
                        startActivity(intent);
                        return true;

                    case R.id.navigation_home:
                        intent = new Intent(AccountActivity.this,LoginActivity.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });
    }
}
