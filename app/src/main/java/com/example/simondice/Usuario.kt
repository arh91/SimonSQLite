package com.example.prueba

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "Usuario")
class Usuario(
    @field:ColumnInfo(name = "nick")
    @field:PrimaryKey val nick: String,
    @field:ColumnInfo(name = "nombre") var nombre: String,
    @field:ColumnInfo(name = "primer_apellido") var primerApellido: String,
    @field:ColumnInfo(name = "contrasenha") private var contraseña: String,
    @field:ColumnInfo(name = "record") private var uRecord: Int

)

