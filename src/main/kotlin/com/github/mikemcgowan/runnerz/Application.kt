package com.github.mikemcgowan.runnerz

import com.github.mikemcgowan.runnerz.user.UserHttpClient
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.client.RestClient
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@SpringBootApplication
class Application {
    @Bean
    fun commandLineRunner(userHttpClient: UserHttpClient): CommandLineRunner = CommandLineRunner {
        val users = userHttpClient.findAll()
        users?.forEach { logger.info(it.toString()) }
    }

    @Bean
    fun userHttpClient(): UserHttpClient {
        val restClient = RestClient.create("https://jsonplaceholder.typicode.com")
        val factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build()
        return factory.createClient(UserHttpClient::class.java)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(Application::class.java)
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
