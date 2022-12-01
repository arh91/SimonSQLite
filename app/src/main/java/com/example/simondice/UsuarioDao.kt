package com.example.prueba

import android.text.Editable
import androidx.room.*


@Dao
interface UsuarioDao {

    @Insert
    fun insertAll(vararg usuarios: Usuario)

    @Insert
    fun insertUser(usuario: Usuario)

    @Update
    fun update(usuario: Usuario)

    @Delete
    fun deleteUser(usuario: Usuario)

    @Query("SELECT * FROM Usuario")
    fun getAll(): List<Usuario>

    @Query("SELECT * FROM Usuario WHERE nick IN (:nicksUsuarios)")
    fun loadAllByIds(nicksUsuarios: Array<String>): List<Usuario>

    @Query("SELECT * FROM Usuario WHERE nombre LIKE :first AND primer_Apellido LIKE :last LIMIT 1")
    fun findByName(first: String, last: String, usuario:Usuario)

    //---------------------------------------------------------------------------------------------------

    @Query("SELECT count(nick) FROM Usuario WHERE nick LIKE :nick")
    fun checkNick(nick: Editable, usuario:Usuario)

    @Query("SELECT contrasenha FROM Usuario WHERE nick LIKE :nick")
    fun getPassword(nick: Editable, usuario:Usuario)
}