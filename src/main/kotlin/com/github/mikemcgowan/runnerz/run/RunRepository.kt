package com.github.mikemcgowan.runnerz.run

import jakarta.annotation.PostConstruct
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.jvm.optionals.getOrNull
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Repository
import org.springframework.util.Assert

@Repository
class RunRepository(private val jdbcClient: JdbcClient) {
    fun findAll(): List<Run> =
        jdbcClient.sql("select * from Run").query(Run::class.java).list()

    fun findById(id: Int): Run? =
        jdbcClient.sql("select * from Run where id = :id").param("id", id).query(Run::class.java).optional().getOrNull()

    fun create(run: Run): Run {
        val updated = jdbcClient.sql("insert into Run(id, title, started_on, completed_on, miles, location) values(?, ?, ?, ?, ?, ?)")
            .params(listOf(run.id, run.title, run.startedOn, run.completedOn, run.miles, run.location.toString()))
            .update();
        Assert.state(updated == 1, "Failed to create run ${run.title}");
        return run
    }

    fun update(id: Int, run: Run): Run? {
        val updated = jdbcClient.sql("update Run set title = ?, started_on = ?, completed_on = ?, miles = ?, location = ? where id = ?")
            .params(listOf(run.title, run.startedOn, run.completedOn, run.miles, run.location.toString(), id))
            .update();
        Assert.state(updated == 1, "Failed to update run ${run.title}");
        return run
    }

    fun delete(id: Int) {
        val updated = jdbcClient.sql("delete from Run where id = :id")
            .param("id", id)
            .update();
        Assert.state(updated == 1, "Failed to delete run $id");
    }

    fun saveAll(runs: List<Run>) =
        runs.forEach { create(it) }

    fun isNotEmpty(): Boolean =
        jdbcClient.sql("select * from Run").query().listOfRows().isNotEmpty()

    companion object {
        private val logger = LoggerFactory.getLogger(RunRepository::class.java)
    }
}
