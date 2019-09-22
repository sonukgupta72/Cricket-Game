package com.sonukgupta72.cricketgame.target

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class TargetViewModel : ViewModel() {

    private var randomScoreLiveData: MutableLiveData<Int> = MutableLiveData()

    fun createRandomNumber() {
        val random = Random()
        randomScoreLiveData.value = random.nextInt(90 - 30) + 30
    }

    fun getRandomScoreLiveData(): LiveData<Int> {
        return randomScoreLiveData
    }
}
