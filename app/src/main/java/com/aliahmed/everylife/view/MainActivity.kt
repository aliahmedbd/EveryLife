package com.aliahmed.everylife.view

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.aliahmed.everylife.R
import com.aliahmed.everylife.adapter.TaskListAdapter
import com.aliahmed.everylife.databinding.ActivityMainBinding
import com.aliahmed.everylife.model.Events
import com.aliahmed.everylife.network.ResponseModel
import com.aliahmed.everylife.utils.Types
import com.aliahmed.everylife.utils.showSnackBar
import com.aliahmed.everylife.viewmodel.TasksViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

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
        listenData()
        checkInternet()
    }

    private fun getTasks() {
        lifecycleScope.launch {
            viewModel.getTasks()
        }
    }

    private fun listenData() {
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
                        Toast.makeText(
                            this@MainActivity,
                            "Data fetched successfully",
                            Toast.LENGTH_SHORT
                        )
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

        if (filteredTasks.isEmpty()) {
            binding.rvTasks.visibility = View.INVISIBLE
            binding.txtEmptyView.visibility = View.VISIBLE
        } else {
            binding.rvTasks.visibility = View.VISIBLE
            binding.txtEmptyView.visibility = View.INVISIBLE
        }

    }

    private fun clickListeners() {
        binding.imgGeneral.setOnClickListener {
            filterType(Types.GENERAL.name.lowercase(), binding.imgGeneral)
        }

        binding.imgHydration.setOnClickListener {
            filterType(Types.HYDRATION.name.lowercase(), binding.imgHydration)
        }

        binding.imgMedication.setOnClickListener {
            filterType(Types.MEDICATION.name.lowercase(), binding.imgMedication)
        }

        binding.imgNutrition.setOnClickListener {
            filterType(Types.NUTRITION.name.lowercase(), binding.imgNutrition)
        }
    }

    private fun filterType(type: String, view: ImageView) {
        if (filterList.contains(type)) {
            filterList.remove(type)
            view.setBackgroundColor(getColor(R.color.white))
        } else {
            filterList.add(type)
            view.setBackgroundColor(getColor(R.color.background_color))
        }
        listenData()
    }

    private fun checkInternet() {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerDefaultNetworkCallback(object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                showSnackBar(
                    this@MainActivity,
                    getString(R.string.back_to_online),
                    duration = Snackbar.LENGTH_SHORT,
                    color = Color.parseColor("#CC000000")
                )
            }

            override fun onLost(network: Network) {
                showSnackBar(
                    this@MainActivity,
                    getString(R.string.offline),
                    duration = Snackbar.LENGTH_INDEFINITE,
                    color = Color.parseColor("#FF0000")
                )
            }
        })
    }
}