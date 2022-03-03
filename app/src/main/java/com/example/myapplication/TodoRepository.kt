package com.example.myapplication

import androidx.lifecycle.LiveData

class TodoRepository(private var todoDao: TodoDao) {

    var allTodos:LiveData<List<Todo>> = todoDao.getAllTodos()
    suspend fun insert(todo: Todo){
        todoDao.insert(todo)
    }

    suspend fun finishTask(id:Long){
        todoDao.finishTask(id)
    }

    suspend fun delete(id:Long){
        todoDao.delete(id)
    }
}