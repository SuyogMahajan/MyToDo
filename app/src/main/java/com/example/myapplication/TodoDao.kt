package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todo:Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("Select * From TodoTable")
    fun getAllTodos():LiveData<List<Todo>>

}