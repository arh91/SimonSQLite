package com.example.prueba

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity

/*Mi base de datos constará de una única tabla a la que llamaré Usuario, que consta de 5 campos,
y asigno como clave principal al campo nick (será único para cada usuario)*/
@Entity(tableName = "Usuario")
class Usuario(
    @field:ColumnInfo(name = "nick")
    @field:PrimaryKey var nick: String,
    @field:ColumnInfo(name = "nombre") var nombre: String,
    @field:ColumnInfo(name = "primer_apellido") var primerApellido: String,
    @field:ColumnInfo(name = "contrasenha") var contraseña: String,
    @field:ColumnInfo(name = "record") var uRecord: Int

)

