package com.springboot.webapp.todo.todo

import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
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
    fun showNewTodoPage(model: ModelMap): String? {
        val todo = Todo(
            0,
            model["name"] as String? ?: "",
            "Default Description",
            LocalDate.now().plusYears(1),
            false)
        model.put("todo", todo)
        return "add-todo"
    }

    @RequestMapping(value = ["add-todo"], method = [RequestMethod.POST])
    fun addNewTodo(model: ModelMap,  @Valid todo: Todo, result: BindingResult): String? {

        if (result.hasErrors()) {
            return "redirect:add-todo"
        }

        val username = model["name"] as String? ?: ""
        todoService.addTodo(
            username,
            todo.description,
            LocalDate.now().plusYears(1),
            false
        )
        return "redirect:todo"
    }

//    @RequestMapping(value = ["add-todo"], method = [RequestMethod.POST])
//    fun addNewTodo(model: ModelMap, todo: @Valid Todo?, result: BindingResult): String? {
//        if (result.hasErrors()) {
//            return "todo"
//        }
//        val username = model["name"] as String?
//        todoService.addTodo(
//            username, todo!!.description,
//            LocalDate.now().plusYears(1), false
//        )
//        return "redirect:list-todos"
//    }
}