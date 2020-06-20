package com.bullshitman.edu105

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bullshitman.edu105.api.EduApi
import com.bullshitman.edu105.model.ResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "EduFetchr"

class EduFetchr  {
    private val eduApi: EduApi
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://edu.105.dubna.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        eduApi = retrofit.create(EduApi::class.java)
    }
    fun fetchResponce(): LiveData<ResponseData> {
        val responseLiveData: MutableLiveData<ResponseData> = MutableLiveData()
        val eduRequest: Call<ResponseData> = eduApi.fetchResponce()
        eduRequest.enqueue(object : Callback<ResponseData> {
            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                Log.e(TAG, "Failed to fetch response", t)
            }

            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                Log.d(TAG, "Response Received")
                val dataResponce: ResponseData? = response.body()
                responseLiveData.value = dataResponce
            }
        })
        return responseLiveData
    }
}