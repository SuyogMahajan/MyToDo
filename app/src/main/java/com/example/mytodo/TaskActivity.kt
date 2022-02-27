package com.example.mytodo

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.mytodo.databinding.ActivityTaskBinding
import com.example.mytodo.todoroomdatabase.AppDatabase
import com.example.mytodo.todoroomdatabase.TodoModel
import com.example.mytodo.todoroomdatabase.todoRepo
import kotlinx.coroutines.*
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*
const val DB_NAME  = "todo.db"

var categorylist = arrayOf( "Codechef","Personal","Business","Educational","Workout")

class TaskActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding:ActivityTaskBinding
    private lateinit var calendar: Calendar
    private lateinit var calendarListener: DatePickerDialog.OnDateSetListener

    private lateinit var timeListener: TimePickerDialog.OnTimeSetListener


    var finalDate = 0L
    var finalTime = 0L

//    val dao = db.TodoDao()
//    val repo = todoRepo(dao)
//    val viewModel = TodoViewModel(repo)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.NewTaskDate.setOnClickListener(this)
        binding.NewTaskTime.setOnClickListener(this)
        binding.NewTaskBtnSave.setOnClickListener(this)

        setSpinnerOptions()

    }

    private fun setSpinnerOptions() {
       var categoryAdapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,categorylist)
       binding.NewTaskSpinner.adapter = categoryAdapter
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                 binding.NewTaskDate.id->{
                     setListener()
                 }
                binding.NewTaskTime.id->{
                    setTimeListener()
                }

                binding.NewTaskBtnSave.id->{
                      var name = binding.NewTaskName.text.toString()
                      var des = binding.NewTaskDes.text.toString()
                      var cat = binding.NewTaskSpinner.selectedItem.toString()

                        val replyIntent = Intent()

                        val data = TodoModel(name,des,finalDate,finalTime,cat)
                        replyIntent.putExtra(EXTRA_REPLY,data )
                        setResult(Activity.RESULT_OK, replyIntent)

                    finish()
                }
            }
            }
        }

    private fun setTimeListener() {
        calendar = Calendar.getInstance()
        timeListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY,hourOfDay)
            calendar.set(Calendar.MINUTE,minute)

            updateTimeDate()
        }

        val timePickerDialog = TimePickerDialog(this,timeListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false)
        timePickerDialog.show()
    }

    @SuppressLint("SimpleDateFormat")
    private fun updateTimeDate() {
        val Timeformate = "h:mm a"
        val sdf = SimpleDateFormat(Timeformate)
        finalTime = calendar.timeInMillis
        binding.NewTaskTime.setText(sdf.format( calendar.time))
    }

    private fun setListener() {
        calendar = Calendar.getInstance()
        calendarListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->

            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,month)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)

            updateDate()
        }

        val datePickerDialog = DatePickerDialog(
            this,calendarListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()

    }

    @SuppressLint("SimpleDateFormat")
    private fun updateDate(){
        val dateformate = "EEE, d MMM yyyy"
        val sdf = SimpleDateFormat(dateformate)
        finalDate = calendar.timeInMillis

        binding.NewTaskDate.setText(sdf.format(calendar.time))

        binding.NewTaskTimeLay.visibility = View.VISIBLE

    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }

}