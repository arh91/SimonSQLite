package com.example.simondice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.prueba.SimonDatabase
import com.example.prueba.Usuario


class MainActivity : AppCompatActivity() {

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val registrarseBtn=findViewById<Button>(R.id.btnRegistrarse)
        registrarseBtn.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        val db = Room.databaseBuilder(
            applicationContext,
            SimonDatabase::class.java, "simonDB"
        ).build()

        val usuarioDao = db.usuarioDao()
        val users: List<Usuario> = usuarioDao.getAll()

    }
}