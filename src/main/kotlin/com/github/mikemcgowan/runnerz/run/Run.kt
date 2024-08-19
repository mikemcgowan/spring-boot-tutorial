package com.github.mikemcgowan.runnerz.run

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Positive
import java.time.LocalDateTime

data class Run(
    val id: Int,
    @field:NotEmpty val title: String,
    val startedOn: LocalDateTime,
    val completedOn: LocalDateTime,
    @field:Positive val miles: Int,
    val location: Location,
) {
    init {
        if (!completedOn.isAfter(startedOn)) throw IllegalArgumentException("'Completed on' must be after 'started on'")
    }
}
