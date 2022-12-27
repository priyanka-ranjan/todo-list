package com.springboot.webapp.todo.repository

import com.springboot.webapp.todo.todo.Todo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoRepository : JpaRepository<Todo, Int> {

    fun findByUsername(username: String): MutableList<Todo>
}