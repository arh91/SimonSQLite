package com.example.prueba

import android.text.Editable
import androidx.room.*


@Dao
interface UsuarioDao {

    @Insert
    fun insertAll(vararg usuarios: Usuario)

    @Insert
    fun insertUser(usuario: Usuario)  //Función que inserta un objeto Usuario con los valores que le hayamos asignado.

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

    /*Función que devuelve el número de veces que aparece un determinado nick de Usuario en nuestra base de datos
    El nick que va a buscar será el que le pasemos como parámetro*/
    @Query("SELECT count(nick) FROM Usuario WHERE nick LIKE :nick")
    fun checkNick(nick: String): Int


    /*Función que devuelve la contraseña asociada a un nick de usuario que le pasamos como parámetro*/
    @Query("SELECT contrasenha FROM Usuario WHERE nick LIKE :nick")
    fun getPassword(nick: String): String


    /*Función que devuelve el nombre asociado al nick de usuario que le pasamos como parámetro*/
    @Query("SELECT nombre FROM Usuario WHERE nick LIKE :nick")
    fun getName(nick: String): String


    /*Función que devuelve el primer apellido asociado al nick de usuario que le pasamos como parámetro*/
    @Query("SELECT primer_apellido FROM Usuario WHERE nick LIKE :nick")
    fun getSurname(nick: String): String


    /*Función que devuelve el record asociado al nick de usuario que le pasamos como parámetro*/
    @Query("SELECT record FROM Usuario WHERE nick LIKE :nick")
    fun getRecord(nick: String): Int

}