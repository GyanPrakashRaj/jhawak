package com.glide.baymax.myglide.utils;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/**
 * Created by baymax on 2016/4/25.
 */
public class NetWorkUtil {
    /**
     * 判断当前网络是否连接
     */
    public static boolean isNetWorkConnected(Context context){
        boolean state;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            state = true;
        }else {
            state = false;
        }
        return state;
    }
    /**
     * 判断当前网络的连接方式是否为WIFI
     */
    public static boolean isWifiConnected(Context context){
        boolean state;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(networkInfo != null && networkInfo.isConnected()){
            state = true;
        }else {
            state = false;
        }
        return state;
    }
}
