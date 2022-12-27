package com.springboot.webapp.todo.todo

import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class TodoService {

    companion object {
        var todosCount: Int = 0

        private val todo1 = Todo(++todosCount,"username", "Learn AWS", LocalDate.now().plusYears(1), false)
        private val todo2 = Todo(++todosCount,"username", "Learn Azure", LocalDate.now().plusYears(2), false)
        private val todo3 = Todo(++todosCount,"username", "Learn FullStack", LocalDate.now().plusYears(3), false)
        val list = arrayOf(todo1, todo2, todo3).toMutableList()
    }

    fun findByUsername(username:String): Array<Todo> {
        return list.filter { it.username == username }.toTypedArray()
    }

    fun addTodo(username: String?, description: String?, targetDate: LocalDate?, done: Boolean) {
        val todo = Todo(
            ++todosCount,
            username!!,
            description!!,
            targetDate!!,
            done
        )
        list.add(todo)
    }

    fun deleteById(id: Int) {
        list.removeIf { it.id == id }
    }

    fun findById(id: Int): Todo {
        return  list.first { it.id == id }
    }

    fun updateTodo(newtodo: Todo) {
        val todo = findById(newtodo.id)
        todo.description = newtodo.description
    }

}