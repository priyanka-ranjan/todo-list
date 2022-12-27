package com.springboot.webapp.todo.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SpringSecurityConfiguration {
    //LDAP or Database
    //In memory

    @Bean
    fun createUserDetailsManager(): InMemoryUserDetailsManager {
        val userDetails1 = createNewUser("username1", "password")
        val userDetails2 = createNewUser("username2", "password")

        return InMemoryUserDetailsManager(userDetails1, userDetails2)
    }

    private fun createNewUser(username: String, password: String): UserDetails? {
        val encoder = { input: String -> passwordEncoder().encode(input) }

        val userDetails = User
            .builder()
            .passwordEncoder(encoder)
            .username(username)
            .password(password)
            .roles("USER", "ADMIN")
            .build()
        return userDetails
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http.authorizeHttpRequests { auth ->
            auth.anyRequest().authenticated()
        }
        http.formLogin(Customizer.withDefaults())
        http.csrf().disable()
        http.headers().frameOptions().disable()
        return http.build()
    }
}