package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application):AndroidViewModel(application) {

    val allTodos:LiveData<List<Todo>>
    val repository:TodoRepository

    init {
        val dao = TodoDataBase.getDataBase(application).getTodoDao()
        repository = TodoRepository(dao)
        allTodos = repository.allTodos
    }

    fun insert(todo: Todo) = viewModelScope.launch(Dispatchers.IO){
       repository.insert(todo)
    }

    fun delete(todo: Todo) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(todo)
    }

}