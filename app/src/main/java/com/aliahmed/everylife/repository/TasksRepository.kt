package com.aliahmed.everylife.repository

import android.content.Context
import com.aliahmed.everylife.datasource.TasksLocalDataSource
import com.aliahmed.everylife.datasource.TasksRemoteDataSource
import com.aliahmed.everylife.model.BaseResponse
import com.aliahmed.everylife.model.Events
import com.aliahmed.everylife.utils.InternetConnectivityUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class TasksRepository(
    private val context: Context,
    private val tasksRemoteDataSource: TasksRemoteDataSource,
    private val localDataSource: TasksLocalDataSource
) {

    suspend fun getTasks(): Flow<BaseResponse> {
        return if (InternetConnectivityUtils(context).isInternetAvailable()) {
            flow {
                val response = tasksRemoteDataSource.getTasks()
                val data = BaseResponse(success = response.isSuccessful, message = response.message(), events = response.body())
                saveDataLocally(response.body())
                emit(data)
            }
        }else{
            flow {
                emit( localDataSource.getTasks())
            }
        }
    }

    private suspend fun saveDataLocally(tasks : List<Events>?){
        tasks?.let { localDataSource.saveTasks(it) }
    }

}