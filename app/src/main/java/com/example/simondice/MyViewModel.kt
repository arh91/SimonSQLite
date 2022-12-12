package com.example.simondice

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.prueba.SimonDatabase

class MyViewModel (application: Application) : AndroidViewModel(application){

    val rondas = mutableListOf<Int>()
    val rondaliveData = MutableLiveData<MutableList<Int>>()


    fun addList(ronda: Int){
        rondas.add(ronda)
        rondaliveData.value = rondas
    }

}