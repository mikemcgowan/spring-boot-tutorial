package com.github.mikemcgowan.runnerz

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import com.github.mikemcgowan.runnerz.Application
import com.github.mikemcgowan.runnerz.run.Run
import com.github.mikemcgowan.runnerz.run.Location
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@SpringBootApplication
class Application {
    @Bean
    fun commandLineRunner(): CommandLineRunner =
        CommandLineRunner { _ ->
            val now = LocalDateTime.now()
            val run = Run(1, "First run", now, now.plus(1, ChronoUnit.HOURS), 5, Location.OUTDOOR)
            logger.info(run.toString())
        }

    companion object {
        private val logger = LoggerFactory.getLogger(Application::class.java)
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
