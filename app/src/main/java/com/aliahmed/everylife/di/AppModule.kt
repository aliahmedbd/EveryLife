package com.aliahmed.everylife.di

import com.aliahmed.everylife.datasource.TasksLocalDataSource
import com.aliahmed.everylife.datasource.TasksRemoteDataSource
import com.aliahmed.everylife.network.TasksApi
import com.aliahmed.everylife.repository.TasksRepository
import com.aliahmed.everylife.viewmodel.TasksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {

    factory {
        val retrofit: Retrofit = get()
        retrofit.create(TasksApi::class.java)
    }

    factory { TasksRemoteDataSource(get()) }

    factory { TasksLocalDataSource(get()) } // TODO: Have to create the dao injection

    factory { TasksRepository(get(), get()) }

    viewModel { TasksViewModel(get()) }

}




