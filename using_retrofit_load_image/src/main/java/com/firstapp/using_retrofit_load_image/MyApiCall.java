package com.firstapp.using_retrofit_load_image;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApiCall {
    //https://run.mocky.io.v3/c4de48b8-5b93-4f07-94bc-a3e339b1de5d


    @GET("c4de48b8-5b93-4f07-94bc-a3e339b1de5d")
    Call<List<ListModel>> getList();

}

