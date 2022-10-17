package com.aliahmed.everylife.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliahmed.everylife.model.BaseResponse
import com.aliahmed.everylife.model.Events
import com.aliahmed.everylife.network.ResponseModel
import com.aliahmed.everylife.repository.TasksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response

class TasksViewModel(private val repository: TasksRepository) : ViewModel() {

    val uiUpdates =
        MutableStateFlow<ResponseModel<BaseResponse>>(ResponseModel.Idle("Idle State"))

    suspend fun getTasks() {
        uiUpdates.emit(ResponseModel.Loading())
        repository.getTasks().collect {
            viewModelScope.launch {
                if (it.success == true) {
                    uiUpdates.emit(ResponseModel.Success(it))
                } else {
                    uiUpdates.emit(ResponseModel.Error(it.message))
                }
            }
        }
    }

    fun filterData(tasks : List<Events>, filteredTask : List<String>) : List<Events>{
        return tasks.filter { it.type in filteredTask }
    }
}