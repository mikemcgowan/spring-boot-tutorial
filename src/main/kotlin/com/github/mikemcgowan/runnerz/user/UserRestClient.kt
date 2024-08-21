package com.github.mikemcgowan.runnerz.user

import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class UserRestClient(val builder: RestClient.Builder) {
    private val restClient: RestClient =
        builder.baseUrl("https://jsonplaceholder.typicode.com").build()

    fun findAll(): List<User>? =
        restClient.get().uri("/users").retrieve().body(object : ParameterizedTypeReference<List<User>>() {})

    fun findById(id: Int): User? =
        restClient.get().uri("/users/{id}", id).retrieve().body(User::class.java)
}
