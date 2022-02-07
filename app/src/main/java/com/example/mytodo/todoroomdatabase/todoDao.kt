package com.example.mytodo.todoroomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface todoDao {

    @Insert
    suspend fun insertTask(todo: TodoModel)

    @Query("SELECT * FROM TodoModel WHERE isFinished != -1")
    suspend fun getTask():List<TodoModel>

    @Query("UPDATE TodoModel SET isFinished = -1 WHERE id == :id")
    suspend fun finishTask(id:Long)

    @Query("DELETE FROM TodoModel WHERE id == :id")
    suspend fun deleteTask(id:Long)

}