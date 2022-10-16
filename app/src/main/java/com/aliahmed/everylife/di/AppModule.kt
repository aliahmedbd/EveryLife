package com.aliahmed.everylife.di

import com.aliahmed.everylife.datasource.TasksLocalDataSource
import com.aliahmed.everylife.datasource.TasksRemoteDataSource
import com.aliahmed.everylife.network.TasksApi
import com.aliahmed.everylife.repository.TasksRepository
import com.aliahmed.everylife.viewmodel.TasksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {

    factory {
        val retrofit: Retrofit = get()
        retrofit.create(TasksApi::class.java)
    }

    factory { TasksRemoteDataSource(tasksApi = get()) }

    factory { TasksLocalDataSource(tasksDao = get()) }

    factory { TasksRepository(tasksRemoteDataSource = get(), localDataSource = get()) }

    viewModel { TasksViewModel(repository = get()) }

}




