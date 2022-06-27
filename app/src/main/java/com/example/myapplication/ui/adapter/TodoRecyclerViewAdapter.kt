package com.example.myapplication.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.models.Todo
import com.example.myapplication.databinding.TodoItemBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class TodoRecyclerViewAdapter(var context: Context,var resources:Resources):RecyclerView.Adapter<TodoRecyclerViewAdapter.ViewHolder>() {

    var list = ArrayList<Todo>()

    inner class ViewHolder(val binding: TodoItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      return ViewHolder(TodoItemBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.name.text = list[position].name
        holder.binding.timeVar.text = convertTime(list[position].time)
        holder.binding.dateVar.text = convertDate(list[position].date)
        holder.binding.category.text = list[position].category

        val colours = resources.getIntArray(R.array.random_colours)
        holder.binding.viewCardColour.setBackgroundColor(colours[Random.nextInt(colours.size)])
    }
    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList:List<Todo>){
        list.clear()

        if(newList.isNotEmpty()) {
            list.addAll(newList)
        }

        notifyDataSetChanged()
    }

    @SuppressLint("SimpleDateFormat")
    private fun convertDate(date: Long): String {

        var sdf = SimpleDateFormat("EEE,d MMM yyyy")
        return sdf.format(Date(date)).toString()

    }

    override fun getItemId(position: Int): Long {
        return list[position].id
    }

    @SuppressLint("SimpleDateFormat")
    private fun convertTime(time: Long): String {

        var sdf = SimpleDateFormat("h:mm a")
        return sdf.format(Date(time)).toString()

    }
}