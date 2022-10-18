package com.aliahmed.everylife.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migrations {
    // 2.6.0
    val all: Array<Migration>
        get() = arrayOf(MIGRATION_1_2)

    private val MIGRATION_1_2 = object : Migration(7, 8) {
        override fun migrate(db: SupportSQLiteDatabase) {
            TODO("We can add migrations if required according to the versions")
        }
    }
}