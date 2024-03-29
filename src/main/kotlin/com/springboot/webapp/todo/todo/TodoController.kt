package com.springboot.webapp.todo.todo

import jakarta.validation.Valid
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
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
        val todoList = todoService.findByUsername(getLoggedinUsername())
        model.addAttribute("todos", todoList)
        return "todo"
    }

    //GET, POST
    @RequestMapping(value = ["add-todo"], method = [RequestMethod.GET])
    fun showNewTodoPage(model: ModelMap): String? {
        val todo = Todo(
            0,
            getLoggedinUsername(),
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

        val username = getLoggedinUsername()
        todo.username = username
        todoService.addTodo(todo)
        return "redirect:todo"
    }

    @RequestMapping("delete-todo")
    fun deleteTodo(@RequestParam id: Int): String {
        todoService.deleteById(id)
        return "redirect:todo"
    }

    @RequestMapping(value = ["update-todo"], method = [RequestMethod.GET])
    fun showUpdateTodoPage(@RequestParam id: Int, model: ModelMap): String? {
        val todo = todoService.findById(id)
        model.addAttribute("todo", todo)
        return "add-todo"
    }

    @RequestMapping("update-todo", method = [RequestMethod.POST])
    fun showUpdateTodoPage(@RequestParam id: Int, @Valid todo:Todo, model: ModelMap, result: BindingResult) : String {

        if (result.hasErrors()) {
            return "add-todo"
        }

        todoService.updateTodo(todo)
        return "redirect:todo"
    }

    //    private fun getLoggedinUsername(model: ModelMap) = model["name"] as String? ?: ""
    private fun getLoggedinUsername(): String {
        val authentication = SecurityContextHolder.getContext().authentication
        return authentication.name
    }
}