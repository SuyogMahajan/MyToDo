package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    lateinit var viewModel: TodoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val recyclerViewAdapter = TodoRecyclerViewAdapter(this,resources)
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = recyclerViewAdapter

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(application)).get(TodoViewModel::class.java)

        viewModel.allTodos.observe(this, Observer {list->
            list?.let {
                recyclerViewAdapter.updateList(it)
            }
        })

        binding.FloatingAction.setOnClickListener{
            val intent = Intent(this@MainActivity,TaskActivity::class.java)
            startActivity(intent)
        }
            }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.HistoryBtn->{
                startActivity(Intent(this,HistoryActivity::class.java))
            }

            R.id.Search->{

            }
        }
        return super.onOptionsItemSelected(item)
    }
}

