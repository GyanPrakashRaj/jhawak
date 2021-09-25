package com.glide.baymax.myglide.net;

/**
 * Created by baymax on 2016/4/25.
 */
public class GankApi {
    private static String GANK_URL = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/";

    /**
     * URL 地址
     * @param count  请求个数
     * @param page   页码
     */
    public static String getGankUrl(int count,int page){
        return GANK_URL + count + "/" + page;
    }
}
