package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding:ActivityHistoryBinding
    private lateinit var viewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerViewAdapter = TodoRecyclerViewAdapter(this,resources)
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = recyclerViewAdapter

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(application)).get(TodoViewModel::class.java)

        viewModel.hist.observe(this, Observer {
            recyclerViewAdapter.updateList(it)
        })

        binding.btnReset.setOnClickListener {
            viewModel.deleteAllHist()
        }
    }
}