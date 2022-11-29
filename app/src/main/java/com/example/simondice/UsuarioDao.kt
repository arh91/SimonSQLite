package com.example.prueba

import androidx.room.*


@Dao
interface UsuarioDao {

    @Insert
    fun insertAll(vararg usuarios: Usuario)

    @Update
    fun update(usuario: Usuario)

    @Delete
    fun delete(usuario: Usuario)

    @Query("SELECT * FROM Usuario")
    fun getAll(): List<Usuario>

    @Query("SELECT * FROM Usuario WHERE nick IN (:idsUsuarios)")
    fun loadAllByIds(idsUsuarios: IntArray): List<Usuario>

    @Query("SELECT * FROM Usuario WHERE nombre LIKE :first AND primer_Apellido LIKE :last LIMIT 1")
    fun findByName(first: String, last: String, usuario:Usuario)

}