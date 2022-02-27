package com.example.mytodo.todoroomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class TodoModel(

    var name:String,
    var description:String,
    var date:Long,
    var time:Long,
    var category:String,
    var isFinished: Int = -1,

    @PrimaryKey(autoGenerate = true)
    var id:Long = 0

) : Serializable
