package com.springboot.webapp.todo.todo

import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.Collections.emptyList

@Service
class TodoService {

    companion object {
        private val todo1 = Todo(1,"username", "Learn AWS", LocalDate.now().plusYears(1), false)
        private val todo2 = Todo(2,"username", "Learn Azure", LocalDate.now().plusYears(2), false)
        private val todo3 = Todo(3,"username", "Learn FullStack", LocalDate.now().plusYears(3), false)
        val list: Array<Todo> = arrayOf(todo1, todo2, todo3 )
    }

    fun findByUsername(username:String): Array<Todo> {
        return list
    }

}