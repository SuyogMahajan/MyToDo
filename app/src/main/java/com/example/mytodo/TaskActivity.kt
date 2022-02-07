package com.example.mytodo

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.room.Room
import com.example.mytodo.databinding.ActivityTaskBinding
import com.example.mytodo.todoroomdatabase.AppDatabase
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*
const val DB_NAME  = "todo.db"

class TaskActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding:ActivityTaskBinding
    private lateinit var calendar: Calendar
    private lateinit var calendarListener: DatePickerDialog.OnDateSetListener

    private lateinit var timeListener: TimePickerDialog.OnTimeSetListener

    val db by lazy {
        Room.databaseBuilder(this,AppDatabase::class.java, DB_NAME)
    }

    var categorylist = arrayOf( "Codechef","Personal","Business","Educational","Workout")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.NewTaskDate.setOnClickListener(this)
        binding.NewTaskTime.setOnClickListener(this)

        setSpinnerOptions(categorylist)

    }

    private fun setSpinnerOptions(categorylist: Array<String>) {
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
            }
            }
        }

    private fun setTimeListener() {
        calendar = Calendar.getInstance()
        timeListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY,hourOfDay)
            calendar.set(Calendar.MINUTE,minute)
            updateTimeDate()
        }

        val timePickerDialog = TimePickerDialog(this,timeListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false)
        timePickerDialog.show()
    }

    private fun updateTimeDate() {
        val Timeformate = "h:mm a"
        val sdf = SimpleDateFormat(Timeformate)

        binding.NewTaskTime.setText(sdf.format(calendar.time))
    }

    private fun setListener() {
        calendar = Calendar.getInstance()
        calendarListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

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

    private fun updateDate(){
        val dateformate = "EEE, d MMM yyyy"
        val sdf = SimpleDateFormat(dateformate)

        binding.NewTaskDate.setText(sdf.format(calendar.time))
        binding.NewTaskTimeLay.visibility = View.VISIBLE

    }

}