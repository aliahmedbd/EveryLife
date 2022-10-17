package com.aliahmed.everylife.datasource

import com.aliahmed.everylife.db.TasksDao
import com.aliahmed.everylife.model.BaseResponse
import com.aliahmed.everylife.model.Events

class TasksLocalDataSource (private val tasksDao: TasksDao) {

    suspend fun getTasks(): BaseResponse {
        val data = tasksDao.getAllTasks()
        return if(data.isEmpty())
            BaseResponse(success = false, message = "Database contains no data", tasksDao.getAllTasks())
        else
            BaseResponse(success = true, message = "Success", tasksDao.getAllTasks())
    }

    suspend fun saveTasks(tasks : List<Events>){
        tasksDao.clearTasks()
        tasksDao.insertAllTasks(events = tasks)
    }

}