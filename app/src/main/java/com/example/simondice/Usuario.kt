package com.example.prueba

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "Usuario")
class Usuario(
    @field:ColumnInfo(name = "nick")
    @field:PrimaryKey var nick: String,
    @field:ColumnInfo(name = "nombre") var nombre: String,
    @field:ColumnInfo(name = "primer_apellido") var primerApellido: String,
    @field:ColumnInfo(name = "contrasenha") var contraseña: String,
    @field:ColumnInfo(name = "record") var uRecord: Int

)

