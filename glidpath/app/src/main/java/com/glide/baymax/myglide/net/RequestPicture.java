package com.glide.baymax.myglide.net;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
/**
 * Created by baymax on 2016/4/25.
 */
public class RequestPicture<T> extends Request<T>{
    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private final Response.Listener<T> listener;

    public RequestPicture(String url,Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.clazz = clazz;
        this.listener = listener;
    }
    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            /**
             * 得到返回的数据
             */
            String resultStr = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
            /**
             * 转化成对象
             */
            return Response.success(gson.fromJson(resultStr,clazz), HttpHeaderParser.parseCacheHeaders(networkResponse));
        }catch (Exception e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T t) {
        listener.onResponse(t);
    }
}
