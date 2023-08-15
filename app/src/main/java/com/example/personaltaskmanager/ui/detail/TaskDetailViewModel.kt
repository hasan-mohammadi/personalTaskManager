package com.example.personaltaskmanager.ui.detail

import androidx.lifecycle.ViewModel
import com.example.personaltaskmanager.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(val repository: TaskRepository) : ViewModel() {
    var remainingTime: RemainingTime? = null
    /** for better performance remaining time will be computed each minute*/
    fun countDownTimerFlow(period: Long, deadlineDate: Date) = flow {
        updateRemainingTime(deadlineDate)
        if (remainingTime!=null) {
            while (true) {
                emit(remainingTime)
                remainingTime?.second = remainingTime?.second!! - 1
                if (remainingTime!!.second < 0)
                    updateRemainingTime(deadlineDate)
                if (remainingTime==null)break
                delay(period)
            }
        }
    }

     fun updateRemainingTime(deadlineDate: Date) {
        val currentDate = Date()

        val diff = deadlineDate.time - currentDate.time
        if (diff < 0){
            remainingTime= null
            return
        }

        val dayMs = 1000 * 60 * 60 * 24
        val hourMs = 1000 * 60 * 60
        val minMs = 1000 * 60
        remainingTime= RemainingTime(
            day = diff / dayMs,
            hour = (diff % dayMs) / hourMs,
            minute = ((diff % dayMs) % hourMs) / minMs,
            second = (((diff % dayMs) % hourMs) % minMs) / 1000
        )
    }

}

data class RemainingTime(var day: Long, var hour: Long, var minute: Long, var second: Long)
