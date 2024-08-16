package com.github.mikemcgowan.runnerz

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RunnerzApplication

fun main(args: Array<String>) {
	runApplication<RunnerzApplication>(*args)
}
