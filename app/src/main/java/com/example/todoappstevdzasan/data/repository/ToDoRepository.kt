package com.example.todoappstevdzasan.data

import androidx.lifecycle.LiveData
import com.example.todoappstevdzasan.data.models.ToDoData

class ToDoRepository(private val toDoDaO:ToDoDao) {
    val getAllData: LiveData<List<ToDoData>> = toDoDaO.getAllData()

    suspend fun insertData(toDoData: ToDoData){
        toDoDaO.insertData(toDoData)
    }
}