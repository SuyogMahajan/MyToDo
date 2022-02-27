package com.example.myapplication

import androidx.lifecycle.LiveData

class TodoRepository(private var todoDao: TodoDao) {

    var allTodos:LiveData<List<Todo>> = todoDao.getAllTodos()
    suspend fun insert(todo: Todo){
        todoDao.insert(todo)
    }

    suspend fun delete(todo: Todo){
        todoDao.delete(todo)
    }
}