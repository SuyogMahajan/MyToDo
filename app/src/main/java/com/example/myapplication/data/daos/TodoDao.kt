package com.example.myapplication.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.data.models.Todo

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todo: Todo)

    @Query("Delete From TodoTable Where id =:uid")
    suspend fun delete(uid:Long)

    @Query("Delete From TodoTable Where finished == 1")
    suspend fun deleteAllHist()

    @Query("Select * From TodoTable Where finished == 0")
    fun getAllTodos():LiveData<List<Todo>>

    @Query("Select * From TodoTable Where finished == 1")
    fun getHistory():LiveData<List<Todo>>

    @Query("Update TodoTable Set finished = 1  Where id = :uid")
    suspend fun finishTask(uid:Long)

}