package com.aliahmed.everylife.network

import com.aliahmed.everylife.model.BaseResponse
import retrofit2.Response
import retrofit2.http.GET

interface TasksApi {

    @GET("tasks.json")
    suspend fun getTasks() : Response<BaseResponse>

}