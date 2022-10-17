package com.aliahmed.everylife.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.aliahmed.everylife.R
import com.aliahmed.everylife.adapter.TaskListAdapter
import com.aliahmed.everylife.databinding.ActivityMainBinding
import com.aliahmed.everylife.model.Events
import com.aliahmed.everylife.network.ResponseModel
import com.aliahmed.everylife.utils.Types
import com.aliahmed.everylife.viewmodel.TasksViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: TasksViewModel by viewModel()
    private lateinit var adapter: TaskListAdapter
    private var filterList: MutableList<String> =
        mutableListOf("general", "medication", "hydration", "nutrition")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        clickListeners()
        getTasks()
        getData()
    }

    private fun getTasks() {
        lifecycleScope.launch {
            viewModel.getTasks()
        }
    }

    private fun getData() {
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
                        Toast.makeText(this@MainActivity, "Data fetched successfully", Toast.LENGTH_SHORT)
                            .show()
                        if (it.data?.events.isNullOrEmpty()) {
                            Toast.makeText(
                                this@MainActivity,
                                "No data found.",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            it.data?.events?.let { it1 -> setDataToList(it1) }
                        }
                    }
                }
            }
        }

    }

    private fun setDataToList(tasks: List<Events>) {
        val filteredTasks = viewModel.filterData(tasks, filterList)
        binding.rvTasks.layoutManager = LinearLayoutManager(this)
        adapter = TaskListAdapter(filteredTasks)
        binding.rvTasks.adapter = adapter

    }

    private fun clickListeners() {
        binding.imgGeneral.setOnClickListener {
            if (filterList.contains("general")) {
                filterList.remove("general")
                binding.imgGeneral.setBackgroundColor(getColor(R.color.white))
            } else {
                filterList.add("general")
                binding.imgGeneral.setBackgroundColor(getColor(R.color.background_color))
            }
            getData()
        }

        binding.imgHydration.setOnClickListener {
            if (filterList.contains("hydration")) {
                filterList.remove("hydration")
                binding.imgHydration.setBackgroundColor(getColor(R.color.white))
            } else {
                filterList.add("hydration")
                binding.imgHydration.setBackgroundColor(getColor(R.color.background_color))
            }
            getData()
        }

        binding.imgMedication.setOnClickListener {
            if (filterList.contains("medication")) {
                filterList.remove("medication")
                binding.imgMedication.setBackgroundColor(getColor(R.color.white))
            } else {
                filterList.add("medication")
                binding.imgMedication.setBackgroundColor(getColor(R.color.background_color))
            }
            getData()
        }

        binding.imgNutrition.setOnClickListener {
            if (filterList.contains("nutrition")) {
                filterList.remove("nutrition")
                binding.imgNutrition.setBackgroundColor(getColor(R.color.white))
            } else {
                filterList.add("nutrition")
                binding.imgNutrition.setBackgroundColor(getColor(R.color.background_color))
            }
            getData()
        }
    }
}