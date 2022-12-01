package com.example.simondice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.prueba.SimonDatabase
import com.example.prueba.Usuario


class MainActivity : AppCompatActivity() {

    val usuario = Usuario()

    val db = Room.databaseBuilder(
        applicationContext,
        SimonDatabase::class.java, "simonDB"
    ).build()


    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nick = findViewById<EditText>(R.id.editTextNickname)
        val contraseña = findViewById<EditText>(R.id.editTextContraseña)

        val nickIntroducido = nick.text;
        val contraseñaIntroducida = contraseña.text;

        val usuarioDao = db.usuarioDao()
        val existeNick = usuarioDao.checkNick(nickIntroducido, usuario)
        val contraseñaRegistrada = usuarioDao.getPassword(nickIntroducido, usuario)

        val inicioBtn=findViewById<Button>(R.id.btnIniciarSesion)
        inicioBtn.setOnClickListener {
            /*if(existeNick == 0){
                println("El nick introducido no existe")
            }else{

            }*/
        }

        val registrarseBtn=findViewById<Button>(R.id.btnRegistrarse)
        registrarseBtn.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

    }
}