package com.example.simondice

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.prueba.SimonDatabase
import com.example.prueba.Usuario
import kotlinx.coroutines.*
import java.util.Observer

abstract class ThirdActivity : AppCompatActivity()  {


    var iniciar: Button? = null
    var espera1 = Handler()
    var espera2 = Handler()
    lateinit var botones: Array<Button?>
    lateinit var sonido: Array<MediaPlayer?>
    lateinit var numeros: IntArray
    lateinit var ordenJugador: IntArray
    var botonesPulsados = 0
    private var timeOn = 1000
    private var timeOff = 1000
    var enabledPlay = false
    var ronda = 0
    var puntuacion = 0
    var estado = "off"

    lateinit var nick:String
    lateinit var nombre:String
    lateinit var primerApellido:String
    lateinit var contraseña:String
    abstract var record:Int

    val usuario = Usuario(nick, nombre, primerApellido, contraseña, record)

    val db = Room.databaseBuilder(
        applicationContext,
        SimonDatabase::class.java, "simonDB"
    ).build()

    val usuarioDao = db.usuarioDao()

    val miModelo by viewModels<MyViewModel>()
    val datosDao = miModelo.db.usuarioDao()

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        ordenJugador = IntArray(4)
        numeros = IntArray(4)
        botones = arrayOfNulls(4)
        iniciar = findViewById(R.id.playButton) as Button?
        botones[0] = findViewById(R.id.blueButton) as Button?
        botones[1] = findViewById(R.id.redButton)  as Button?
        botones[2] = findViewById(R.id.greenButton) as Button?
        botones[3] = findViewById(R.id.yellowButton) as Button?
        sonido = arrayOfNulls(4)
        sonido[0] = MediaPlayer.create(this, R.raw.moneda)
        sonido[1] = MediaPlayer.create(this, R.raw.saltolargo)
        sonido[2] = MediaPlayer.create(this, R.raw.vaja)
        sonido[3] = MediaPlayer.create(this, R.raw.ladrilloroto)

        nick = intent.getStringExtra("nickIntroducido").toString()
        nombre = usuarioDao.getName(nick)
        primerApellido = usuarioDao.getSurname(nick)
        contraseña = intent.getStringExtra("contraseñaIntroducida").toString()
        record = usuarioDao.getRecord(nick)


        iniciar?.setOnClickListener{

            generarSecuencia(iniciar!!)
        }
    }

    fun clickarBoton(v: View) {
        val indice: Int
        val id = v.id
        val b = v as Button
        indice = if (id == R.id.blueButton) {
            v.setBackgroundResource(R.color.azulfuerte)
            sonido[0]!!.start()
            0
        } else if (id == R.id.redButton) {
            v.setBackgroundResource(R.color.rojofuerte)
            sonido[1]!!.start()
            1
        } else if (id == R.id.greenButton) {
            v.setBackgroundResource(R.color.verdefuerte)
            sonido[2]!!.start()
            2
        } else {
            v.setBackgroundResource(R.color.amarillofuerte)
            sonido[3]!!.start()
            3
        }
        val handler = Handler()
        handler.postDelayed({ resetear(b.id) }, 500)
        if (enabledPlay) {
            ordenJugador[botonesPulsados] = indice
            botonesPulsados++
            if (botonesPulsados == 4) {
                comprobarSecuencia()
            }
        }
    }

    fun generarSecuencia(v: View) { // funcion con retardo
        var jobIniciarPartida: Job? = null

        jobIniciarPartida = GlobalScope.launch(Dispatchers.Main){ // launch Lanza una courutina y continua
            for (i in numeros.indices) {
                numeros[i] = (Math.random() * 4).toInt()
                val b = botones[numeros[i]]
                println(numeros[i])
                if (b!!.id == R.id.blueButton) {
                    /*espera1.postDelayed({
                        b.setBackgroundResource(R.color.azulfuerte)
                        sonido[0]!!.start()
                    }, 1000)*/
                    delay(1000)
                } else if (b.id == R.id.redButton) {
                    delay(1000)
                } else if (b.id == R.id.greenButton) {
                    delay(1000)
                } else {
                    delay(1000)
                }
                espera2.postDelayed({ resetear(b.id) }, timeOff.toLong())
                timeOn += 400
                timeOff += 200
            }
            timeOn = 1000
            timeOff = 1000
        }
        Log.d("Courutina","Lanzada:" + " " + jobIniciarPartida.toString()) // esto ocurre a espensas de la courutina
    }


    fun resetear(id: Int) {
        if (id == R.id.blueButton) {
            botones[0]!!.setBackgroundResource(R.color.azul)
        } else if (id == R.id.redButton) {
            botones[1]!!.setBackgroundResource(R.color.rojo)
        } else if (id == R.id.greenButton) {
            botones[2]!!.setBackgroundResource(R.color.verde)
        } else {
            botones[3]!!.setBackgroundResource(R.color.amarillo)
        }
    }

    fun setRondas(rondas: Int){
        val wave = findViewById<TextView>(R.id.editTextRonda)
        wave.setText(rondas)
    }

    fun comprobarSecuencia() {
        var acertados = 0
        for (i in numeros.indices) {
            if (numeros[i] != ordenJugador[i]) {
                ronda = 0
                estado = "off"
                Toast.makeText(this, "Lo siento, has fallado.", Toast.LENGTH_SHORT).show()
            } else {
                acertados++
            }
            if (acertados == 4) {
                ronda+=1
                Toast.makeText(this, "Enhorabuena, has acertado!", Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Ronda "+ronda.toString(), Toast.LENGTH_SHORT).show();

                miModelo.addList(ronda)

                miModelo.rondaliveData.observe(
                    this,
                    Observer(
                        fun(listaRondas : MutableList<Int>){
                            println("Array: "+listaRondas.toString())
                        }
                    )

                )
                setRondas(ronda)

            }
                if(ronda>record){
                    record = ronda
                    usuario.uRecord = record
                }
            }
            generarSecuencia(iniciar!!)
        }
        enabledPlay = false
        botonesPulsados = 0
        numeros = IntArray(4)
        ordenJugador = IntArray(4)
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