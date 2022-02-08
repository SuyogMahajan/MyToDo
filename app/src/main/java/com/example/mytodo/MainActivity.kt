package com.example.mytodo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.databinding.ActivityMainBinding
import com.example.mytodo.todoroomdatabase.TodoModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.rv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        var adptr = TodoAdapter(listOf(TodoModel("Codechef","hello",21112001,255,"Codechef",-1)),this)
        binding.rv.adapter = adptr

        val i = Intent(this,TaskActivity::class.java)

        binding.floatingActionButton.setOnClickListener {
           startActivity(i)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
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