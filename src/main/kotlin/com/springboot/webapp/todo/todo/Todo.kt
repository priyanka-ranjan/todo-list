package com.springboot.webapp.todo.todo

import jakarta.validation.constraints.Size
import java.time.LocalDate

data class Todo(
    val id: Int,
    val username: String?,

    @field:Size(min = 10, message = "Enter at least 10 characters")
    val description: String,
    val targetDate: LocalDate?,
    val done: Boolean = false
)
