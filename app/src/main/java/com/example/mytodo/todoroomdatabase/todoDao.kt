package com.example.mytodo.todoroomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface todoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(todo: TodoModel)

    @Query("SELECT * FROM TodoModel WHERE isFinished != -1")
    fun getTask(): Flow<List<TodoModel>>

    @Query("UPDATE TodoModel SET isFinished = -1 WHERE id == :id")
    fun finishTask(id:Long)

    @Query("DELETE FROM TodoModel WHERE id == :id")
    fun deleteTask(id:Long)

    @Query("DELETE FROM TodoModel")
     fun deleteAll()

}