package com.example.simondice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.prueba.SimonDatabase
import com.example.prueba.Usuario


class MainActivity : AppCompatActivity() {

    val db = Room.databaseBuilder(
        applicationContext,
        SimonDatabase::class.java, "simonDB"
    ).build()


    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var nick = findViewById<EditText>(R.id.editTextNickname)
        var contraseña = findViewById<EditText>(R.id.editTextContraseña)

        var nickIntroducido = nick.text.toString()
        var contraseñaIntroducida = contraseña.text.toString()

        val usuarioDao = db.usuarioDao()
        val existeNick = usuarioDao.checkNick(nickIntroducido)
        val contraseñaRegistrada = usuarioDao.getPassword(nickIntroducido)

        val inicioBtn=findViewById<Button>(R.id.btnIniciarSesion)
        inicioBtn.setOnClickListener {
            if(existeNick == 0){
                Toast.makeText(this, "El nick introducido no existe", Toast.LENGTH_LONG).show()
            }else {
                if(contraseñaIntroducida != contraseñaRegistrada){
                    Toast.makeText(this, "Clave incorrecta", Toast.LENGTH_LONG).show()
                }else{
                    val intentThird = Intent(this, ThirdActivity::class.java)
                    intent.putExtra("nickIntroducido", nickIntroducido)
                    intent.putExtra("contraseñaIntroducida", contraseñaIntroducida)
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

    override fun onStart(){
        super.onStart();
        Log.d("Estado","onStart")
    }

    override fun onResume(){
        super.onResume();
        Log.d("Estado","onResume")
    }

    override fun onPause() {
        super.onPause();
        Log.d("Estado","onResume")
    }

    override fun onRestart() {
        super.onRestart();
        Log.d("Estado","onRestart");
    }

    override fun onDestroy(){
        super.onDestroy();
        Log.d("Estado","onDestroy")
    }
}