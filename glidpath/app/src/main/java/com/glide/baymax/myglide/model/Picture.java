package com.glide.baymax.myglide.model;

import java.util.ArrayList;

/**
 * Created by baymax on 2016/4/25.
 */
public class Picture {
    private String error;
    private ArrayList<Results> results;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ArrayList<Results> getResults() {
        return results;
    }

    public void setResults(ArrayList<Results> results) {
        this.results = results;
    }
}
