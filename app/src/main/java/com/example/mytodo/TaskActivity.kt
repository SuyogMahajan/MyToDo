package com.example.mytodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mytodo.databinding.ActivityTaskBinding

class TaskActivity : AppCompatActivity() {
    private lateinit var binding:ActivityTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}