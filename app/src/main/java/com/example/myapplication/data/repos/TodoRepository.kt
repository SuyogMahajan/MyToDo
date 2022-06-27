package com.example.myapplication.data.repos

import androidx.lifecycle.LiveData
import com.example.myapplication.data.daos.TodoDao
import com.example.myapplication.data.models.Todo

class TodoRepository(private var todoDao: TodoDao) {

    var allTodos:LiveData<List<Todo>> = todoDao.getAllTodos()
    var hist:LiveData<List<Todo>> = todoDao.getHistory()

    suspend fun insert(todo: Todo){
        todoDao.insert(todo)
    }

    suspend fun deleteAllHist(){
        todoDao.deleteAllHist()
    }

    suspend fun finishTask(id:Long){
        todoDao.finishTask(id)
    }

    suspend fun delete(id:Long){
        todoDao.delete(id)
    }
}