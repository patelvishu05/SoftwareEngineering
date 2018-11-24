package com.coderunners.spoofify;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private HomeFragment homeFragment;
    private LibraryFragment libraryFragment;
    private AccountFragment accountFragment;
    private SearchFragment  searchFragment;
    private PlayerFragment playerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        homeFragment = new HomeFragment();
        libraryFragment = new LibraryFragment();
        accountFragment = new AccountFragment();
        searchFragment = new SearchFragment();
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
                    case R.id.nav_search:
                       // mMainNav.setItemBackgroundResource(R.color.colorAccent);
                        setFragment(searchFragment);
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
    public void changeFragment(int id)
    {
        switch(id)
        {
            case 3:
                setFragment(playerFragment);
                break;
            default:
        }
    }
}