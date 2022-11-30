package com.example.simondice

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.prueba.SimonDatabase
import kotlinx.coroutines.*

class ThirdActivity : AppCompatActivity()  {

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
    var record = 0
    var estado = "off"

    val db = Room.databaseBuilder(
        applicationContext,
        SimonDatabase::class.java, "simonDB"
    ).build()

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

        /*iniciar?.setOnClickListener{
            // cada vez que le doy al click empieza la cuenta atrás
            generarSecuencia(iniciar!!)
        }*/
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
                    /*espera1.postDelayed({
                        b.setBackgroundResource(R.color.rojofuerte)
                        sonido[1]!!.start()
                    }, 1000)*/
                    delay(1000)
                } else if (b.id == R.id.greenButton) {
                    /*espera1.postDelayed({
                        b.setBackgroundResource(R.color.verdefuerte)
                        sonido[2]!!.start()
                    }, 1000)*/delay(1000)
                } else {
                    /*espera1.postDelayed({
                        b.setBackgroundResource(R.color.amarillofuerte)
                        sonido[3]!!.start()
                    }, 1000)*/delay(1000)
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



    /*fun generarSecuencia(v: View?) {
        enabledPlay = true

        for (i in numeros.indices) {
            numeros[i] = (Math.random() * 4).toInt()
            val b = botones[numeros[i]]
            println(numeros[i])
            if (b!!.id == R.id.blueButton) {
                espera1.postDelayed({
                    b.setBackgroundResource(R.color.azulfuerte)
                    sonido[0]!!.start()
                }, 1000)
            } else if (b.id == R.id.redButton) {
                espera1.postDelayed({
                    b.setBackgroundResource(R.color.rojofuerte)
                    sonido[1]!!.start()
                }, 1000)
            } else if (b.id == R.id.greenButton) {
                espera1.postDelayed({
                    b.setBackgroundResource(R.color.verdefuerte)
                    sonido[2]!!.start()
                }, 1000)
            } else {
                espera1.postDelayed({
                    b.setBackgroundResource(R.color.amarillofuerte)
                    sonido[3]!!.start()
                }, 1000)
            }
            espera2.postDelayed({ resetear(b.id) }, timeOff.toLong())
            timeOn += 400
            timeOff += 200
        }
        timeOn = 1000
        timeOff = 1000
    }*/

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

    fun comprobarSecuencia() {
        var acertados = 0
        for (i in numeros.indices) {
            if (numeros[i] != ordenJugador[i]) {
                estado = "off"
                Toast.makeText(this, "Lo siento, has fallado.", Toast.LENGTH_SHORT).show()
            } else {
                acertados++
            }
            if (acertados == 4) {
                ronda+=1
                puntuacion+=1
                Toast.makeText(this, "Enhorabuena, has acertado!", Toast.LENGTH_SHORT).show()
                Toast.makeText(this, ronda.toString()+"º ronda", Toast.LENGTH_SHORT).show();
                if(puntuacion==1) {
                    Toast.makeText(this, puntuacion.toString() + " punto", Toast.LENGTH_SHORT).show();
                }
                if(puntuacion > 1){
                    Toast.makeText(this, puntuacion.toString() + " puntos", Toast.LENGTH_SHORT).show();
                }
                if(puntuacion>record){
                    record = puntuacion
                    if(record == 1) {
                        Toast.makeText(
                            this, "Nuevo record: " + record.toString() + " punto", Toast.LENGTH_SHORT).show();
                    }
                    if(record > 1) {
                        Toast.makeText(this, "Nuevo record: " + record.toString() + " puntos", Toast.LENGTH_SHORT).show();
                    }
                }
                //generarSecuencia(v)
            }
            enabledPlay = false
            botonesPulsados = 0
            numeros = IntArray(4)
            ordenJugador = IntArray(4)
        }
        puntuacion = 0
    }