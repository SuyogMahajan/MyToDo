package com.example.mytodo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.databinding.TodoItemBinding
import com.example.mytodo.todoroomdatabase.TodoModel

class TodoAdapter(var list:List<TodoModel>,var context:Context):RecyclerView.Adapter<TodoAdapter.getViewHolder>() {
   private lateinit var binding:TodoItemBinding

   class getViewHolder(binding: TodoItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): getViewHolder {
       binding = TodoItemBinding.inflate(LayoutInflater.from(context),parent,false)

       return getViewHolder(binding)
    }

    override fun onBindViewHolder(holder: getViewHolder, position: Int) {
        binding.name.text = list[position].name
        binding.subtittle.text = list[position].description
        binding.dateVar.text = list[position].date.toString()
        binding.timeVar.text = list[position].time.toString()
        binding.category.text = list[position].category
    }

    override fun getItemCount() = list.size

}