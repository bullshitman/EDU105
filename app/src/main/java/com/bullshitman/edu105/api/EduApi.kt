package com.bullshitman.edu105.api

import com.bullshitman.edu105.model.ResponseData
import retrofit2.Call
import retrofit2.http.GET

interface EduApi {
        @GET("view/json")
        fun fetchResponce(): Call<ResponseData>
}