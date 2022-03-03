package com.example.myapplication

import android.R
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityTaskActivtyBinding
import java.text.SimpleDateFormat
import java.util.*

class TaskActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var binding: ActivityTaskActivtyBinding
    private lateinit var calendar: Calendar
    private lateinit var calendarListener: DatePickerDialog.OnDateSetListener
    private lateinit var timeListener: TimePickerDialog.OnTimeSetListener


    lateinit var viewModel: TodoViewModel
    var categorylist = arrayOf( "Personal","Codechef","Business","Educational","Workout")

    var date:Long = 0L
    var time:Long = 0L



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskActivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(application)).get(TodoViewModel::class.java)
        binding.NewTaskDate.setOnClickListener(this)
        binding.NewTaskTime.setOnClickListener(this)
        binding.NewTaskBtnSave.setOnClickListener(this)
        setSpinnerOptions()

    }

    private fun setSpinnerOptions() {
        var categoryAdapter = ArrayAdapter(this,
            R.layout.simple_spinner_dropdown_item,categorylist)
        binding.NewTaskSpinner.adapter = categoryAdapter
    }

    override fun onClick(v: View?) {
        if(v != null){
            when(v.id){
                binding.NewTaskBtnSave.id->{
                   if(binding.NewTaskName.text.isNullOrEmpty() || date == 0L || time == 0L){
                       Toast.makeText(this,"You Must Enter Name ,Date & Time !",Toast.LENGTH_LONG).show()
                   }else{
                       viewModel.insert(Todo(binding.NewTaskName.text.toString(),binding.NewTaskDes.text.toString(),binding.NewTaskSpinner.selectedItem.toString(),time,date))
                       this.finish()
                   }
                }

                binding.NewTaskTime.id->{
                 setTimePicker()
                }

                binding.NewTaskDate.id-> {
                 setDatePicker()
                }
            }
        }
    }

    private fun setDatePicker() {
        calendar = Calendar.getInstance()
        calendarListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->

            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
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
            date = calendar.timeInMillis

            binding.NewTaskDate.setText(sdf.format(calendar.time))

            binding.NewTaskTimeLay.visibility = View.VISIBLE

        }

    private fun updateTimeDate() {
        val Timeformate = "h:mm a"
        val sdf = SimpleDateFormat(Timeformate)
        time = calendar.timeInMillis
        binding.NewTaskTime.setText(sdf.format( calendar.time))
    }

    private fun setTimePicker() {
        calendar = Calendar.getInstance()
        timeListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY,hourOfDay)
            calendar.set(Calendar.MINUTE,minute)

            updateTimeDate()
        }

        val timePickerDialog = TimePickerDialog(this,timeListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false)
        timePickerDialog.show()
    }
}