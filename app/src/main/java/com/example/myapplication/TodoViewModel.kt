package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application):AndroidViewModel(application) {

    val allTodos:LiveData<List<Todo>>
    val hist:LiveData<List<Todo>>
    val repository:TodoRepository

    init {
        val dao = TodoDataBase.getDataBase(application).getTodoDao()
        repository = TodoRepository(dao)
        allTodos = repository.allTodos
        hist = repository.hist
    }

     fun deleteAllHist()= viewModelScope.launch(Dispatchers.IO){
        repository.deleteAllHist()
    }
    fun insert(todo: Todo) = viewModelScope.launch(Dispatchers.IO){
       repository.insert(todo)
    }

    fun finishTask(id:Long)= viewModelScope.launch(Dispatchers.IO){
       repository.finishTask(id)
    }

    fun delete(id:Long) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(id)
    }

}