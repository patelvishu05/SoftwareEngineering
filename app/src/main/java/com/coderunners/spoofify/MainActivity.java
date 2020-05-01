package com.coderunners.spoofify;

import android.content.SharedPreferences;
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

import com.coderunners.spoofify.Model.NewsPost;
import com.coderunners.spoofify.Model.Posts;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private List<NewsPost> newsposts = new ArrayList<>();
    private HomeFragment homeFragment;
    private LibraryFragment libraryFragment;
    private AccountFragment accountFragment;
    private AlarmFragment alarmFragment;
    private PlayerFragment playerFragment;
    public Posts posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Manually adding NewsPosts to test out NewsFeed
        NewsPost np = new NewsPost();
        np.setAuthor("Spoofify Employee 1");
        np.setTitle("Your EDM Premiere: Tisoki – All Like That [Never Say Die]");
        np.setUrlToImage("https://www.youredm.com/wp-content/uploads/2018/11/all-like-that-1000.png");
        np.setUrl("https://fs.bitcoinmagazine.com/img/images/bcash1_week.max-800x800.jpg");
        np.setDescription(" “All Like That” is a certified banger. While Tisoki may primarily produce dubstep, we’ve heard him branch out before. He’s even done bass house before this year, on “Bring It Back” out on Monstercat. Though, “All Like That” is another beast entirely.  ");
        np.setPublishedAt("2018-11-23T10:42:59Z");
        newsposts.clear();
        newsposts.add(np);
        NewsPost np1 = new NewsPost();
        np1.setAuthor("Patrick Montes");
        np1.setTitle("Fritsgod Delivers “4 Chains” Video & Drops 'Dying to Get Home'");
        np1.setUrlToImage("https://image-cdn.hypb.st/https%3A%2F%2Fhypebeast.com%2Fimage%2F2018%2F11%2Ffritsgod-4-chains-music-video-dying-to-get-home-stream-0.jpg");
        np1.setDescription("The rising Inglewood rapper continues his come-up.");
        np1.setPublishedAt("2018-11-23T1:42:59Z");
        NewsPost np2 = new NewsPost();
        np2.setTitle("Best New Tracks: PnB Rock, Pivot Gang, Kelly Rowland and Malibu Ken (Aesop Rock & TOBACCO)");
        np2.setDescription("New heat for the holiday weekend.");
        np2.setPublishedAt("2018-11-20T7:42:59Z");
        np2.setUrlToImage("https://image-cdn.hypb.st/https%3A%2F%2Fhypebeast.com%2Fimage%2F2018%2F11%2Fbest-new-tracks-pnb-rock-pivot-gang-kelly-rowland-malibu-ken-aesop-rock-tobacco-11.jpg?fit=max&cbr=1&q=90&w=750&h=500");
        np2.setAuthor("Emmanuel Maduakolam");

        newsposts.add(np1);
        newsposts.add(np2);

        //newsposts.clear();
        // String myJson= inputStreamToString(getResources().openRawResource(R.raw.newsfeed_data));
        Gson gson = new Gson();
        posts = new Posts();
        posts.setNewsPost(newsposts);
        String json = gson.toJson(posts);


        SharedPreferences SP = getSharedPreferences("newsfeed.json", 0);

        SharedPreferences.Editor editor= SP.edit();
        editor.putString("posts", json);
        editor.commit();





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