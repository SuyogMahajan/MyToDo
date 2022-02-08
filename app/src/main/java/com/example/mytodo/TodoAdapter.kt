package com.example.mytodo

import android.content.Context
import android.content.res.Resources
import android.content.res.Resources.*
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.databinding.TodoItemBinding
import com.example.mytodo.todoroomdatabase.TodoModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class TodoAdapter(var list:List<TodoModel>,var context:Context,val resources: Resources):RecyclerView.Adapter<TodoAdapter.getViewHolder>() {
   private lateinit var binding:TodoItemBinding

   class getViewHolder(binding: TodoItemBinding):RecyclerView.ViewHolder(binding.root){

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): getViewHolder {
       binding = TodoItemBinding.inflate(LayoutInflater.from(context),parent,false)

       return getViewHolder(binding)
    }

    override fun onBindViewHolder(holder: getViewHolder, position: Int) {

        binding.name.text = list[position].name
        binding.subtittle.text = list[position].description
        updateDate(list[position].date)
        updateTimeDate(list[position].time)
        binding.category.text = list[position].category

        val colours = resources.getIntArray(R.array.random_colours)
        binding.viewCardColour.setBackgroundColor(colours[Random.nextInt(colours.size)])

    }

    override fun getItemCount() = list.size

    private fun updateDate(time:Long){
        val dateformate = "EEE, d MMM yyyy"
        val sdf = SimpleDateFormat(dateformate)

        binding.dateVar.text = sdf.format(Date(time))
    }

    private fun updateTimeDate(time:Long) {
        val Timeformate = "h:mm a"
        val sdf = SimpleDateFormat(Timeformate)

       binding.timeVar.text = sdf.format(Date(time))
    }
}