package com.coderunners.spoofify.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source {

    @SerializedName("postId")
    @Expose
    private Source postId;

    @SerializedName("employeeId")
    @Expose
    private String employeeId;

    public Source getPostId() {
        return postId;
    }

    public void setPostId(Source postId) {
        this.postId = postId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
