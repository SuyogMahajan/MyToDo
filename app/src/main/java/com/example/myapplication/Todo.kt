package com.example.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TodoTable")
class Todo(
    val name:String,
    val disc:String,
    val category:String,
    val time:Long,
    val date:Long
) {
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0L
}