package com.coderunners.spoofify;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Button;
import com.coderunners.spoofify.R;
//import com.coderunners.spoofify.Model.AccountActivity;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private HomeFragment homeFragment;
    private LibraryFragment libraryFragment;
    private AccountFragment accountFragment;
    private AlarmFragment alarmFragment;
    private PlayerFragment playerFragment;
    private Button makeAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        homeFragment = new HomeFragment();
        libraryFragment = new LibraryFragment();
        accountFragment = new AccountFragment();
        alarmFragment = new AlarmFragment();
        playerFragment = new PlayerFragment();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragment(homeFragment);
        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);
        //Listener: Change view when user selects navigation icon
        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.nav_home:
                       // mMainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(homeFragment);
                        return true;
                    case R.id.nav_playist:
                       // mMainNav.setItemBackgroundResource(R.color.colorAccent);
                        setFragment(libraryFragment);
                        return true;
                    case R.id.nav_account:
                        //mMainNav.setItemBackgroundResource(R.color.colorAccent);
                        setFragment(accountFragment);
                        return true;
                    case R.id.nav_player:
                        //mMainNav.setItemBackgroundResource(R.color.colorAccent);
                        setFragment(playerFragment);
                        return true;
                    case R.id.nav_alarm:
                       // mMainNav.setItemBackgroundResource(R.color.colorAccent);
                        setFragment(alarmFragment);
                        return true;

                    default:
                        return false;
                }


            }
        });

    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void changeFragment(int id, String streamName, String streamExt)
    {
        switch(id)
        {
            case 1:
                mMainNav.setSelectedItemId(R.id.nav_home);
                break;
            case 2:
                mMainNav.setSelectedItemId(R.id.nav_playist);
                break;
            case 3:
                mMainNav.setSelectedItemId(R.id.nav_alarm);
                break;
            case 4:
                playerFragment.updateSongName(streamName, streamExt);
                mMainNav.setSelectedItemId(R.id.nav_player);
                break;
            case 5:
                mMainNav.setSelectedItemId(R.id.nav_account);
                break;
            default:
        }
    }
}