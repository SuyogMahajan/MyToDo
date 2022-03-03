package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomMasterTable
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodoDataBase :RoomDatabase(){
    abstract  fun getTodoDao():TodoDao

    companion object{

        @Volatile
        private var INSTANCE :TodoDataBase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDataBase(context: Context):TodoDataBase{

            return INSTANCE ?: synchronized(this){
                    val instance = Room.databaseBuilder(context.applicationContext,TodoDataBase::class.java,"todo_database").build()

                   INSTANCE = instance
                  instance
                }
        }
    }
}