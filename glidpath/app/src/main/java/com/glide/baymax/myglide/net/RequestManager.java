package com.glide.baymax.myglide.net;

import com.android.volley.Request;
import com.glide.baymax.myglide.base.MyApplication;

/**
 * Created by baymax on 2016/4/25.
 */
public class RequestManager {

    private RequestManager(){
    }

    public static void addRequest(Request<?> request, Object obj){
        if(obj != null){
            request.setTag(obj);
        }
        MyApplication.getVolleyQueue().add(request);
    }

    public static void cancelRequest(Object obj){
        MyApplication.getVolleyQueue().cancelAll(obj);
    }
}
