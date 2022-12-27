package com.springboot.webapp.todo.todo

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.validation.constraints.Size
import java.time.LocalDate

@Entity
data class Todo(

    @Id
    @GeneratedValue
    val id: Int = 0,
    var username: String? = "",

    @field:Size(min = 10, message = "Enter at least 10 characters")
    var description: String = "",
    val targetDate: LocalDate? = LocalDate.now(),
    val done: Boolean = false
)
