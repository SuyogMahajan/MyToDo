package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todo:Todo)

    @Query("Delete From TodoTable Where id =:uid")
    suspend fun delete(uid:Long)

    @Query("Select * From TodoTable Where finished == 0")
    fun getAllTodos():LiveData<List<Todo>>

    @Query("Update TodoTable Set finished = 1  Where id = :uid")
    suspend fun finishTask(uid:Long)

}