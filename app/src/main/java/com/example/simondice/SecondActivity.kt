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

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val nombre = findViewById<EditText>(R.id.editTextNombre)
        val apellido = findViewById<EditText>(R.id.editTextApellido)
        val nick = findViewById<EditText>(R.id.editTextNickname)
        val contraseña = findViewById<EditText>(R.id.editTextContraseña)

        val nombreIntroducido = nombre.text;
        val apellidoIntroducido = apellido.text;
        val nickIntroducido = nick.text;
        val contraseñaIntroducida = contraseña.text;

        val db = Room.databaseBuilder(
            applicationContext,
            SimonDatabase::class.java, "simonDB"
        ).build()

        val registroBtn=findViewById<Button>(R.id.btnRegistro)
        registroBtn.setOnClickListener {
            val usuarioDao = db.usuarioDao()
            usuarioDao.insertUser(usuario)

            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }


    }
}