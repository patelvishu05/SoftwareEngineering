package com.coderunners.spoofify.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Posts {

    @SerializedName("status")
    @Expose
    private String status; //SUCCESS or FAIL

    @SerializedName("totalResult")
    @Expose
    private int totalResult; //number of posts read

    @SerializedName("posts")
    @Expose
    private List<NewsPost> newsPost;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public List<NewsPost> getNewsPost() {
        return newsPost;
    }

    public void setNewsPost(List<NewsPost> newsPost) {
        this.newsPost = newsPost;
    }
}
