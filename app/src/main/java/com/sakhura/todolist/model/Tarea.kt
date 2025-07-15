package com.sakhura.todolist.model

import java.sql.Timestamp

data class Tarea(
    var titulo: String,
    var completada: Boolean = false,
    val timestamp: Long = System.currentTimeMillis()
)