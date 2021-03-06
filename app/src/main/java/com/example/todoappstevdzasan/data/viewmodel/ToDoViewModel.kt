package com.example.todoappstevdzasan.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todoappstevdzasan.data.ToDoDatabase
import com.example.todoappstevdzasan.data.repository.ToDoRepository
import com.example.todoappstevdzasan.data.models.ToDoData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application:Application):AndroidViewModel(application){
    private val toDoDao=
        ToDoDatabase.getDatabase(
            application
        ).toDoDao()
    private val repository:ToDoRepository

    val getAllData: LiveData<List<ToDoData>>

    init{
        repository=ToDoRepository(toDoDao)
        getAllData=repository.getAllData
    }

    fun insertData(toDoData: ToDoData){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertData(toDoData)
        }
    }

    fun updateData(toDoData: ToDoData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(toDoData)
        }
    }
}