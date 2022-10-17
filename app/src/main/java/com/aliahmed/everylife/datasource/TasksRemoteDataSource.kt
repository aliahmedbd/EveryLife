package com.aliahmed.everylife.datasource

import com.aliahmed.everylife.model.BaseResponse
import com.aliahmed.everylife.network.TasksApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class TasksRemoteDataSource(private val tasksApi: TasksApi) {
    suspend fun getTasks(): Response<BaseResponse>  = tasksApi.getTasks()
}