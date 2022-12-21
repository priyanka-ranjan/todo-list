package com.springboot.webapp.todo.login

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.SessionAttributes

@Controller
@SessionAttributes("name")
class LoginController(
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

    @RequestMapping(value = arrayOf("/login"), method = arrayOf(RequestMethod.GET))
    fun loginJSP(): String {
        return "login"
    }

    @RequestMapping(value = arrayOf("/login"), method = arrayOf(RequestMethod.POST))
    fun goToWelcomePage(@RequestParam name:String, @RequestParam password:String, model:ModelMap): String {
        model.put("name", name)
        if (authenticationService.authenticate(name,password)) {
            return "welcome"
        }

        return "login"
    }
}