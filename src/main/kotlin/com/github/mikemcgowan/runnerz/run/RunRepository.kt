package com.github.mikemcgowan.runnerz.run

import jakarta.annotation.PostConstruct
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import org.springframework.stereotype.Repository

@Repository
class RunRepository {
    private val runs = mutableListOf<Run>()

    fun findAll(): List<Run> = runs
    fun findById(id: Int): Run? = runs.find { it.id == id }
    fun delete(id: Int) = runs.removeIf { it.id == id }

    fun create(run: Run): Run {
        runs.add(run)
        return run
    }

    fun update(id: Int, run: Run): Run? {
        val existingRun = findById(id)
        if (existingRun == null) return null
        runs.set(runs.indexOf(existingRun), run)
        return run
    }

    @PostConstruct
    fun init() {
        val now = LocalDateTime.now()
        val run1 = Run(1, "Monday morning", now, now.plus(1, ChronoUnit.HOURS), 5, Location.OUTDOOR)
        val run2 = Run(2, "Wednesday evening", now, now.plus(1, ChronoUnit.HOURS), 6, Location.OUTDOOR)
        runs.addAll(listOf(run1, run2))
    }
}
