package com.aliahmed.everylife.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aliahmed.everylife.model.Events

@Dao
abstract class TasksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAllTasks(events: List<Events>)

    @Query("Select * from Events")
    abstract suspend fun getAllTasks(): List<Events>

    @Query("Delete from Events")
    abstract suspend fun clearTasks()
}