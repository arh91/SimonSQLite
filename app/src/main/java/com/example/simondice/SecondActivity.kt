package com.example.simondice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.prueba.SimonDatabase
import kotlinx.coroutines.*

class SecondActivity : AppCompatActivity() {

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val registroBtn=findViewById<Button>(R.id.btnRegistro)
        registroBtn.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        val db = Room.databaseBuilder(
            applicationContext,
            SimonDatabase::class.java, "simonDB"
        ).build()
    }
}