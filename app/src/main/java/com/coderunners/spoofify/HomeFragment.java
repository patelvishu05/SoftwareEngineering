package com.coderunners.spoofify;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.coderunners.spoofify.Model.NewsPost;
import com.coderunners.spoofify.Model.Posts;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private static final Object MODE_PRIVATE = 0;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<NewsPost>  newsposts = new ArrayList<>();
    private Adapter adapter;
    public String TAG = HomeFragment.class.getSimpleName();
    public Posts posts;

    public HomeFragment() {
        // Required empty public constructor
    }

    public String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);
            return json;
        } catch (IOException e) {
            return null;
        }
    }
    private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("nfdata.json", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //ImageView imageView = (ImageView) getView().findViewById(R.id.foo);
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
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
        //writeToFile(json, this.getContext());

        SharedPreferences SP = this.getContext().getSharedPreferences("newsfeed.json", 0);
        //SharedPreferences.Editor editor= SP.edit();
        //editor.putString("posts", json);
        //editor.commit();
        String jsonToParse = SP.getString("posts", null);


        posts  = gson.fromJson(jsonToParse, Posts.class);
        newsposts = posts.getNewsPost();
        adapter = new Adapter(newsposts, getActivity());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return rootView;
    }

}
