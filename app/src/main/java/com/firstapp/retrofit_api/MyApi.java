package com.firstapp.retrofit_api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApi {

    //https://run.mocky.io/v3/             bc924cc9-b079-4547-a81d-1e578d0bb5aa

    @GET("bc924cc9-b079-4547-a81d-1e578d0bb5aa")
    Call<ModelClass> getData();




}
