package com.github.mikemcgowan.runnerz.run

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/runs")
class RunController(private val repo: RunRepository) {
    @GetMapping fun findAll(): List<Run> =
        repo.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Int): Run? =
        repo.findById(id) ?: throw RunNotFoundException()

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun create(@Valid @RequestBody run: Run): Run =
        repo.create(run)

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Int, @Valid @RequestBody run: Run): Run? =
        repo.update(id, run)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Int) =
        repo.delete(id)
}
