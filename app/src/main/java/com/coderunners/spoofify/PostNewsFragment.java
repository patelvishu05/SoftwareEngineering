package com.coderunners.spoofify;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.coderunners.spoofify.Model.NewsPost;
import com.coderunners.spoofify.Model.Posts;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_WORLD_WRITEABLE;


public class PostNewsFragment extends Fragment {

        Button submitPost;
        EditText postTitle;
        EditText postDescription;
        EditText postUrl;
        Posts posts;
        private List<NewsPost> newsposts = new ArrayList<>();

        public PostNewsFragment() {
            // Required empty public constructor
        }

        public void postNewsPostToJSON(String title, String description, String url){
            SharedPreferences SP = this.getContext().getSharedPreferences("newsfeed.json", 0);
            String jsonToParse = SP.getString("posts", null);
            Gson gson = new Gson();
            posts  = gson.fromJson(jsonToParse, Posts.class);
            newsposts = posts.getNewsPost();
            NewsPost np = new NewsPost();
            np.setTitle(title);
            np.setDescription(description);
            np.setUrlToImage(url);
            np.setAuthor("Employee");
            np.setPublishedAt("2018-12-05T10:25:59Z");
            newsposts.add(np);
            posts.setNewsPost(newsposts);
            String json = gson.toJson(posts);

            SharedPreferences.Editor editor= SP.edit();
            editor.putString("posts", json);
            editor.commit();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_post_news, container, false);
            submitPost= rootView.findViewById(R.id.submitPost);
            postTitle = rootView.findViewById(R.id.EditTextTitle);
            postDescription = rootView.findViewById(R.id.EditTextDescription);
            postUrl = rootView.findViewById(R.id.EditTextURL);

            submitPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    postNewsPostToJSON( postTitle.getText().toString(), postDescription.getText().toString(), postUrl.getText().toString());



                    Fragment fragment = new AccountFragment();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_frame, fragment);
                    fragmentTransaction.commit();
                }
            });
            // Inflate the layout for this fragment
            return rootView;
        }

    }

