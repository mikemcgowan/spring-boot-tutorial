package com.github.mikemcgowan.runnerz.run

import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.ArgumentMatchers
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.http.MediaType
import com.fasterxml.jackson.databind.ObjectMapper
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.Optional

@WebMvcTest(RunController::class)
class RunControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper
    
    @MockBean
    lateinit var repo: RunRepository

    val runs = mutableListOf<Run>()

    @BeforeEach
    fun beforeEach() {
        runs.add(Run(1, "Monday morning run", LocalDateTime.now(), LocalDateTime.now().plus(30, ChronoUnit.MINUTES), 3, Location.INDOOR, 1))
    }

    @Test
    fun shouldFindAllRuns() {
        `when`(repo.findAll()).thenReturn(runs)
        mvc.perform(get("/api/runs"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()", `is`(runs.size)))
    }

    @Test
    fun shouldFindOneRun() {
        val run = runs.first()
        `when`(repo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(run))
        mvc.perform(get("/api/runs/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", `is`(run.id)))
            .andExpect(jsonPath("$.title", `is`(run.title)))
            .andExpect(jsonPath("$.miles", `is`(run.miles)))
            .andExpect(jsonPath("$.location", `is`(run.location.toString())))
    }

    @Test
    fun shouldReturnNotFoundWithInvalidId() {
        mvc.perform(get("/api/runs/99"))
            .andExpect(status().isNotFound())
    }

    @Test
    fun shouldCreateNewRun() {
        val run = Run(2, "Monday morning run", LocalDateTime.now(), LocalDateTime.now().plus(30, ChronoUnit.MINUTES), 3, Location.INDOOR, 1)
        `when`(repo.save(run)).thenReturn(run)
        mvc.perform(post("/api/runs").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(run)))
            .andExpect(status().isCreated())
    }

    @Test
    fun shouldUpdateExistingRun() {
        val run = Run(1, "Monday morning run", LocalDateTime.now(), LocalDateTime.now().plus(30, ChronoUnit.MINUTES), 3, Location.INDOOR, 1)
        `when`(repo.save(run)).thenReturn(run)
        `when`(repo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(run))
        mvc.perform(put("/api/runs").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(run)))
            .andExpect(status().isOk())
    }

    @Test
    fun shouldDeleteRun() {
        val run = runs.first()
        `when`(repo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(run))
        mvc.perform(delete("/api/runs/1"))
            .andExpect(status().isNoContent())
    }
}
