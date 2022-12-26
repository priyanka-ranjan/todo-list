package com.springboot.webapp.todo.todo

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.SessionAttributes
import java.time.LocalDate


@Controller
@SessionAttributes("name")
class TodoController (
    val todoService: TodoService
){

    @RequestMapping("/todo")
    fun goToWelcomePage(model: ModelMap): String {
        val todoList = todoService.findByUsername("username")
        model.addAttribute("todos", todoList)
        return "todo"
    }

    //GET, POST
    @RequestMapping(value = ["add-todo"], method = [RequestMethod.GET])
    fun showNewTodoPage(): String? {
        return "add-todo"
    }

    @RequestMapping(value = ["add-todo"], method = [RequestMethod.POST])
    fun addNewTodo(@RequestParam description: String?, model: ModelMap): String? {
        val username = model["name"] as String?
        todoService.addTodo(
            username,
            description,
            LocalDate.now().plusYears(1),
            false
        )
        return "redirect:todo"
    }
}