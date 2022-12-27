package com.springboot.webapp.todo.welcome

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.SessionAttributes

@Controller
@SessionAttributes("name")
class WelcomeController(
    val authenticationService: AuthenticationService) {

//    companion object {
//        private val loggerWithExplicitClass
//                = LoggerFactory.getLogger(this::class.java)
//    }

    //JSP
    /**If you want to send values to your JSP, you need to create a modelMap. So add it to it.
    @RequestMapping("/login")
    fun loginJSP(@RequestParam param: String, model: ModelMap): String {
        model.put("param", param)
        return "login"
    }**/

    @RequestMapping(value = ["/"], method = [RequestMethod.GET])
    fun welcomeJSP(model: ModelMap): String {
        model.put("name", getLoggedinUsername())
        return "welcome"
    }

    fun getLoggedinUsername(): String {
        val authentication = SecurityContextHolder.getContext().authentication
        return authentication.name
    }
}