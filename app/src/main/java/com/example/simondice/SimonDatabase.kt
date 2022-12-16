package com.example.prueba

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Usuario::class], version = 1)   //Hago referencia a la clase donde se encuentran las tablas para mi base de datos.
abstract class SimonDatabase : RoomDatabase() {       //Extiendo de RoomDatabase para poder alojar la base de datos en SQLite
    abstract fun usuarioDao(): UsuarioDao             //Instancio la clase UsuarioDao donde se encuentran las funciones que utilizará mi app para trabajar con la base de datos.
}