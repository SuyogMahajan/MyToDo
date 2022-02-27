package com.example.mytodo.todoroomdatabase

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class todoRepo(private val tododao: todoDao) {

    val allTodos: Flow<List<TodoModel>> = tododao.getTask()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(todoModel: TodoModel){
        tododao.insertTask(todoModel)
    }

    fun deleteTodo(id:Long){
        tododao.deleteTask(id)
    }

    fun finishTask(id:Long){
        tododao.finishTask(id)
    }

}