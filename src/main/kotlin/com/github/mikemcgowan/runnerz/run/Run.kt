package com.github.mikemcgowan.runnerz.run

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Positive
import java.time.LocalDateTime
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version

data class Run(
    @field:Id val id: Int,
    @field:NotEmpty val title: String,
    val startedOn: LocalDateTime,
    val completedOn: LocalDateTime,
    @field:Positive val miles: Int,
    val location: Location,
    @field:Version val version: Int,
) {
    init {
        if (!completedOn.isAfter(startedOn)) throw IllegalArgumentException("'Completed on' must be after 'started on'")
    }
}
