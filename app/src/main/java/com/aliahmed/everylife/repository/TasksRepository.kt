package com.aliahmed.everylife.repository

import com.aliahmed.everylife.datasource.TasksLocalDataSource
import com.aliahmed.everylife.datasource.TasksRemoteDataSource

class TasksRepository(
    private val tasksRemoteDataSource: TasksRemoteDataSource,
    private val localDataSource: TasksLocalDataSource
) {


}