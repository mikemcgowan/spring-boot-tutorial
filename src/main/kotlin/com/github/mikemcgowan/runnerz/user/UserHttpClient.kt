package com.github.mikemcgowan.runnerz.user

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.service.annotation.GetExchange

interface UserHttpClient {
    @GetExchange("/users")
    fun findAll(): List<User>?

    @GetExchange("/users/{id}")
    fun findById(@PathVariable id: Int): User?
}
