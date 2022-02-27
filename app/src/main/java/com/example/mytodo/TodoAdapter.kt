package com.example.mytodo

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.databinding.TodoItemBinding
import com.example.mytodo.todoroomdatabase.TodoModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class TodoAdapter(var context:Context,var list:ArrayList<TodoModel>):RecyclerView.Adapter<TodoAdapter.getViewHolder>() {


   class getViewHolder(val binding: TodoItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): getViewHolder {

       return getViewHolder( TodoItemBinding.inflate(LayoutInflater.from(context),parent,false)
       )
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: getViewHolder, position: Int) {

        holder.binding.name.text = list[position].name
        holder.binding.subtittle.text = list[position].description
        val dateformate = "EEE,d MMM yyyy"
        val sdf = SimpleDateFormat(dateformate)

        holder.binding.dateVar.text = sdf.format(Date(list[position].date))


        val Timeformate = "h:mm a"
        val sdft = SimpleDateFormat(Timeformate)

        holder.binding.timeVar.text = sdft.format(Date(list[position].time))

        holder.binding.category.text = list[position].category

//        val colours = resources.getIntArray(R.array.random_colours)
//        holder.binding.viewCardColour.setBackgroundColor(colours[Random.nextInt(colours.size)])
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newlist: List<TodoModel>){
        list.clear()
        list.addAll(newlist)
        notifyDataSetChanged()
    }
}