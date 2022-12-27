package com.springboot.webapp.todo.todo

import com.springboot.webapp.todo.repository.TodoRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import kotlin.jvm.optionals.getOrNull

@Service
class TodoService(
    val todoRepository: TodoRepository
) {

//    companion object {
//        var todosCount: Int = 0
//
//        private val todo1 = Todo(++todosCount,"username", "Learn AWS", LocalDate.now().plusYears(1), false)
//        private val todo2 = Todo(++todosCount,"username", "Learn Azure", LocalDate.now().plusYears(2), false)
//        private val todo3 = Todo(++todosCount,"username", "Learn FullStack", LocalDate.now().plusYears(3), false)
//        val list = arrayOf(todo1, todo2, todo3).toMutableList()
//    }

    fun findByUsername(username:String): Array<Todo> {
        return todoRepository.findByUsername(username).toTypedArray()
    }

    fun addTodo(todo:Todo) {
        todoRepository.save(todo)
//        list.add(todo)
    }

    fun deleteById(id: Int) {
        todoRepository.deleteById(id)
//        list.removeIf { it.id == id }
    }

    fun findById(id: Int): Todo {
        return todoRepository.findById(id).orElseThrow()
//        return  list.first { it.id == id }
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