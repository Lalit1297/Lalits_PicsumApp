package com.lalitspicsumapp.network;

import com.lalitspicsumapp.model.Authors;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIinterface {
    @GET("list")
    Call<List<Authors>> getAuthors();
}