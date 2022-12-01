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

        val nickIntroducido = nick.text.toString()
        val contraseñaIntroducida = contraseña.text.toString()

        val usuarioDao = db.usuarioDao()
        val existeNick = usuarioDao.checkNick(nickIntroducido, usuario)
        val contraseñaRegistrada = usuarioDao.getPassword(nickIntroducido, usuario)

        val inicioBtn=findViewById<Button>(R.id.btnIniciarSesion)
        inicioBtn.setOnClickListener {
            if(existeNick == 0){
                Toast.makeText(this, "El nick introducido no existe", Toast.LENGTH_LONG).show()
            }else{
                if(contraseñaIntroducida != contraseñaRegistrada){
                    Toast.makeText(this, "Clave incorrecta", Toast.LENGTH_LONG).show()
                }else{
                    val intentThird = Intent(this, ThirdActivity::class.java)
                    startActivity(intentThird)
                }
            }
        }

        val registrarseBtn=findViewById<Button>(R.id.btnRegistrarse)
        registrarseBtn.setOnClickListener {
            val intentTwo = Intent(this, SecondActivity::class.java)
            startActivity(intentTwo)
        }

    }
}