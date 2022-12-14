package com.example.simondice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.prueba.SimonDatabase
import com.example.prueba.Usuario
import kotlinx.coroutines.*

class SecondActivity : AppCompatActivity() {

    val usuario = Usuario()

    val db = Room.databaseBuilder(
        applicationContext,
        SimonDatabase::class.java, "simonDB"
    ).build()

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val nombre = findViewById<EditText>(R.id.editTextNombre)
        val apellido = findViewById<EditText>(R.id.editTextApellido)
        val nick = findViewById<EditText>(R.id.editTextNickname)
        val contraseña = findViewById<EditText>(R.id.editTextContraseña)

        val nombreIntroducido = nombre.text.toString()
        val apellidoIntroducido = apellido.text.toString()
        val nickIntroducido = nick.text.toString()
        val contraseñaIntroducida = contraseña.text.toString()

        val registroBtn=findViewById<Button>(R.id.btnRegistro)
        registroBtn.setOnClickListener {
            usuario.nick = nickIntroducido
            usuario.nombre = nombreIntroducido
            usuario.primerApellido = apellidoIntroducido
            usuario.contraseña = contraseñaIntroducida
            usuario.uRecord = 0

            val usuarioDao = db.usuarioDao()
            usuarioDao.insertUser(usuario)

            val intentThird = Intent(this, ThirdActivity::class.java)
            startActivity(intentThird)
        }


    }
}