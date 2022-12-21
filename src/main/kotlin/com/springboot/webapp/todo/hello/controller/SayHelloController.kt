package com.springboot.webapp.todo.hello.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SayHelloController {

    @GetMapping("/hello")
    fun sayHello(): String {
        return "Hello! What are you learning today?"
    }

    //HTML
    @GetMapping("/hello-html")
    fun sayHelloHtml(): String {

        val html: StringBuffer = StringBuffer()
        html.append("<html>")
        html.append("<head>")
        html.append("<title> My first HTML Page </title>")
        html.append("</head>")
        html.append("<body>")
        html.append("My first html page with body")
        html.append("</body>")
        html.append("</html>")

        return html.toString()
    }

    //JSP
    //Update the @RestController to @Controller
    @RequestMapping("/hello-jsp")
    fun sayHelloJSP(): String {
        return "hello"
    }
}



