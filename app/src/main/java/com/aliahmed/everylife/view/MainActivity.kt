package com.aliahmed.everylife.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.aliahmed.everylife.R
import com.aliahmed.everylife.databinding.ActivityMainBinding
import com.aliahmed.everylife.network.ResponseModel
import com.aliahmed.everylife.viewmodel.TasksViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: TasksViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        getTasks()
    }

    private fun getTasks() {
        lifecycleScope.launch {
            viewModel.getTasks()
        }

        lifecycleScope.launch {
            viewModel.uiUpdates.collectLatest {
                when (it) {
                    is ResponseModel.Error -> {
                        Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                    }
                    is ResponseModel.Idle -> {
                        Toast.makeText(this@MainActivity, "Idle State", Toast.LENGTH_SHORT)
                            .show()
                    }
                    is ResponseModel.Loading -> {
                        Toast.makeText(this@MainActivity, "Loading ...", Toast.LENGTH_SHORT)
                            .show()
                    }
                    is ResponseModel.Success -> {
                        if (it.data?.events.isNullOrEmpty()) {
                            Toast.makeText(
                                this@MainActivity,
                                "No data found.",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                it.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }

    }
}