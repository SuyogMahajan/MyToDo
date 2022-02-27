package com.example.mytodo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytodo.databinding.ActivityMainBinding
import com.example.mytodo.todoroomdatabase.AppDatabase
import com.example.mytodo.todoroomdatabase.TodoApplication
import com.example.mytodo.todoroomdatabase.TodoModel
import com.example.mytodo.todoroomdatabase.todoRepo
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    private val newWordActivityRequestCode = 1
    private val todoViewModel: TodoViewModel by viewModels {
        TodoViewModelFactory((application as TodoApplication).repo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val reso = resources
        var adapter = TodoAdapter(this,ArrayList<TodoModel>())

//        val list = listOf<TodoModel>(
//            TodoModel("suyog","Mahajan",218387,23818272,"codechef"),
//            TodoModel("suyog","Mahajan",218387,23818272,"codechef"),
//            TodoModel("suyog","Mahajan",218387,23818272,"codechef")
//        )

       binding.rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = adapter
       }

           binding.floatingaction.setOnClickListener {
               val intent = Intent(this@MainActivity, TaskActivity::class.java)
               startActivityForResult(intent, newWordActivityRequestCode)
           }

        todoViewModel.allTodos.observe(this, Observer { list ->
                list.let {
                    adapter.updateList(it)
                }
        })

         }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {

            var todo =  intentData?.getSerializableExtra(TaskActivity.EXTRA_REPLY) as TodoModel
            todoViewModel.insert(todo)

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