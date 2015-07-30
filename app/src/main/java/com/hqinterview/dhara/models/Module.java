package com.hqinterview.dhara.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by USER on 24-07-2015.
 */
public class Module extends CommonHolder{
    @SerializedName("params")
    private List<String> params;

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }
}
