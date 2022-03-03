package com.example.myapplication

import android.content.Intent
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        intitSwipe()

        binding.FloatingAction.setOnClickListener{
            val intent = Intent(this@MainActivity,TaskActivity::class.java)
            startActivity(intent)
        }

            }

    fun intitSwipe() {


        // to listen touch on any recycleview item    taking help of item touch helper

        val simpleTouchHelper = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){

              // this block is for draging action
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                return false
            }


            // this one for swipes left or right which we have passed in arguments of itemTouch helper
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition

                if(direction == ItemTouchHelper.LEFT ){
                     viewModel.finishTask(binding.rv.adapter!!.getItemId(position))
                }else if(direction == ItemTouchHelper.RIGHT){
                     viewModel.delete(binding.rv.adapter!!.getItemId(position))
                }

            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {

               if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
                   val paint = Paint()
               // this will store icon for our swipes like delete and done
                   val itemView = viewHolder.itemView
                   var icon:Bitmap?

                   if(dX > 0){
                       val d = ResourcesCompat.getDrawable(resources,R.drawable.ic_done,theme)
                       icon = drawableToBitmap(d!!)

                       paint.color = Color.parseColor("#388E3C")

                       c.drawRect(
                           itemView.left.toFloat(), itemView.top.toFloat(),
                           itemView.left.toFloat() + dX, itemView.bottom.toFloat(), paint
                       )
                       c.drawBitmap(  icon,itemView.left.toFloat()+40,
                           itemView.top.toFloat() + (itemView.bottom.toFloat() - itemView.top.toFloat() - icon.height.toFloat())/2,
                       paint
                           )

                   }else{
                       val d = ResourcesCompat.getDrawable(resources,R.drawable.ic_delete,theme)
                       icon = drawableToBitmap(d!!)
                       paint.color = Color.parseColor("#D32F2F")

                       c.drawRect(
                           itemView.right.toFloat(), itemView.top.toFloat(),
                           itemView.right.toFloat() + dX, itemView.bottom.toFloat(), paint
                       )

                       c.drawBitmap(
                           icon,
                           itemView.right.toFloat() - icon.width.toFloat()-40,
                           itemView.top.toFloat() + (itemView.bottom.toFloat() - itemView.top.toFloat() - icon.height.toFloat()) / 2,
                           paint
                       )
                   }
                   viewHolder.itemView.translationX = dX

               }else {

                   super.onChildDraw(
                       c,
                       recyclerView,
                       viewHolder,
                       dX,
                       dY,
                       actionState,
                       isCurrentlyActive
                   )
               }
            }

        }
        val itemTouchHelper = ItemTouchHelper(simpleTouchHelper)
        itemTouchHelper.attachToRecyclerView(binding.rv)

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

    private fun drawableToBitmap(drawable: Drawable):Bitmap {
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }
        val bitmap =
            Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(10, 10, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}

