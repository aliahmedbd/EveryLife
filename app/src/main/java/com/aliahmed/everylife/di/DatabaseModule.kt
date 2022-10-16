package com.aliahmed.everylife.di

import com.aliahmed.everylife.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    single { AppDatabase.getInstance(androidApplication()) }

    factory {
        val appDatabase: AppDatabase = get()
        return@factory appDatabase.tasksDao()
    }

}
