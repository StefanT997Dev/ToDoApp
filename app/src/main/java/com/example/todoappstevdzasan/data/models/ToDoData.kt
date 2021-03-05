package com.example.todoappstevdzasan.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todoappstevdzasan.data.models.Priority

@Entity(tableName="todo_table")
data class ToDoData (
    @PrimaryKey(autoGenerate=true)
    var id:Int,
    var title:String,
    var priority: Priority,
    var description:String
)