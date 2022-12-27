package com.springboot.webapp.todo.todo

import com.springboot.webapp.todo.repository.TodoRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import kotlin.jvm.optionals.getOrNull

@Service
class TodoService(
    val todoRepository: TodoRepository
) {

    fun findByUsername(username:String): Array<Todo> {
        return todoRepository.findByUsername(username).toTypedArray()
    }

    fun addTodo(todo:Todo) {
        todoRepository.save(todo)
    }

    fun deleteById(id: Int) {
        todoRepository.deleteById(id)
    }

    fun findById(id: Int): Todo {
        return todoRepository.findById(id).orElseThrow()
    }

    fun updateTodo(newtodo: Todo) {
        try {
            val todo = findById(newtodo.id)
            todo.description = newtodo.description
            todoRepository.save(todo)
        } catch (e: Exception) {
            print("Could not update Todo as it could not find the Todo with exception $e")
        }

    }

}