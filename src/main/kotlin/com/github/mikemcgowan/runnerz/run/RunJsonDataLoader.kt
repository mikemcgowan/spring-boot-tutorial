package com.github.mikemcgowan.runnerz.run

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.IOException
import java.io.InputStream
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class RunJsonDataLoader(
    private val runRepository: RunRepository,
    private val objectMapper: ObjectMapper,
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        if (runRepository.count() > 0) {
            logger.info("Not reading runs from JSON data as database isn't empty")
            return
        }
        try {
            TypeReference::class.java.getResourceAsStream("/data/runs.json").use { inputStream: InputStream ->
                val allRuns = objectMapper.readValue(inputStream, Runs::class.java)
                logger.info("Reading {} runs from JSON data", allRuns.runs.size)
                runRepository.saveAll(allRuns.runs)
            }
        } catch (e: IOException) {
            throw RuntimeException("Failed to read JSON data", e)
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(RunJsonDataLoader::class.java)
    }
}
