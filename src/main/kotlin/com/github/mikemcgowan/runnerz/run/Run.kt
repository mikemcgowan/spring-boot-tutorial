package com.github.mikemcgowan.runnerz.run

import java.time.LocalDateTime

data class Run(
    val id: Int,
    val title: String,
    val startedOn: LocalDateTime,
    val completedOn: LocalDateTime,
    val miles: Int,
    val location: Location,
)
