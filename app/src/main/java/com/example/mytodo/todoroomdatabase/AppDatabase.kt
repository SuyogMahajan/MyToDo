package com.example.mytodo.todoroomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mytodo.DB_NAME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import kotlinx.coroutines.launch

@Database(entities = [TodoModel::class], version = 1, exportSchema = false)
abstract class AppDatabase:RoomDatabase() {
    abstract fun TodoDao():todoDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDataBase(context: Context,
                        scope: CoroutineScope
        ):AppDatabase?{


             if(INSTANCE == null){
              synchronized(AppDatabase::class.java) {
                  INSTANCE = Room.databaseBuilder(
                      context.applicationContext, AppDatabase::class.java,
                      DB_NAME
                  )
                      .fallbackToDestructiveMigration()
                      .addCallback(TodoDatabaseCallback(scope))
                      .build()
              }
              }
            return INSTANCE
        }

        private class TodoDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.TodoDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(todoDao: todoDao) {
             todoDao.deleteAll()
        }
    }
    }