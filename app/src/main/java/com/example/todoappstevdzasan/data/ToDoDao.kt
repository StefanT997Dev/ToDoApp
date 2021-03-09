package com.example.todoappstevdzasan.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todoappstevdzasan.data.models.ToDoData

@Dao
interface ToDoDao {
    @Query("SELECT * FROM todo_table ORDER BY id asc")
    fun getAllData(): LiveData<List<ToDoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(toDoData: ToDoData)

    @Update
    suspend fun updateData(toDoData: ToDoData)
}