package com.github.mikemcgowan.runnerz

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Application {
    /*
    @Bean
    fun commandLineRunner(runRepository: RunRepository): CommandLineRunner = CommandLineRunner {
        val now = LocalDateTime.now()
        val run = Run(1, "First run", now, now.plus(1, ChronoUnit.HOURS), 5, Location.OUTDOOR)
        runRepository.create(run)
    }
    */

    companion object {
        private val logger = LoggerFactory.getLogger(Application::class.java)
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
