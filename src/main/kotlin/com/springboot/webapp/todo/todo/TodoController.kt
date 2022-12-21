package com.springboot.webapp.todo.todo

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes

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
}