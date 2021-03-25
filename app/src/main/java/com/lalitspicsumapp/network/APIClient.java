package com.lalitspicsumapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static String baseurl = "https://picsum.photos/";

    public static Retrofit getClient(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseurl).addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
    public static APIinterface apIinterface(){
        return getClient().create(APIinterface.class);
    }
}
