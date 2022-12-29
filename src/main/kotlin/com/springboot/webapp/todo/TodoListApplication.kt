package com.springboot.webapp.todo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class TodoListApplication

fun main(args: Array<String>) {
    runApplication<TodoListApplication>(*args)
}
