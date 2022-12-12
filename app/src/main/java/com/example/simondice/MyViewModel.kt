package com.example.simondice

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import androidx.room.*

class MyViewModel (application: Application) : AndroidViewModel(application){

    val rondas = mutableListOf<Int>()
    val rondaliveData = MutableLiveData<MutableList<Int>>()

    init {
        rondaliveData.value = rondas
    }

    fun addList(ronda: Int){
        rondas.add(ronda)
        rondaliveData.value = rondas
    }

}