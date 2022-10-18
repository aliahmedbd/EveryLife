package com.aliahmed.everylife.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aliahmed.everylife.model.Events

private const val DB_NAME: String = "everylife.db"

@Database(
    version = 1,
    entities = [Events::class]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tasksDao(): TasksDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase{
            return instance ?: synchronized(this){
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .addMigrations(*Migrations.all)
                .build()
        }
    }
}