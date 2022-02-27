package com.example.mytodo

import android.content.Context
import androidx.lifecycle.*
import com.example.mytodo.todoroomdatabase.TodoModel
import com.example.mytodo.todoroomdatabase.todoRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(val repo:todoRepo):ViewModel() {

    val allTodos: LiveData<List<TodoModel>> = repo.allTodos.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(todoModel: TodoModel) = viewModelScope.launch {
        repo.insert(todoModel)
    }

    fun deleteTask(id:Long){
        repo.deleteTodo(id)
    }

    fun finishTask(id:Long){
        repo.finishTask(id)
    }

}

class TodoViewModelFactory(private val repo:todoRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TodoViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}