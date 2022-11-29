package com.example.prueba

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Usuario::class], version = 1)
abstract class SimonDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
}