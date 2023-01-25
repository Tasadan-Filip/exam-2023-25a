package com.example.exam25a.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exam25a.dao.MyEntityDao
import com.example.exam25a.domain.MyEntity

// Increment db version!!!
@Database(entities = [MyEntity::class], version = 4, exportSchema = false)
abstract class MyEntityRoomDatabase : RoomDatabase() {

    abstract fun myEntityDao(): MyEntityDao

    companion object {
        @Volatile
        private var INSTANCE: MyEntityRoomDatabase? = null

        fun getDatabase(
            context: Context
        ) : MyEntityRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyEntityRoomDatabase::class.java,
                    "entities_database_2021_18"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}