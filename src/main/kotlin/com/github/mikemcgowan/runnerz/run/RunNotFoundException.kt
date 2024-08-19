package com.github.mikemcgowan.runnerz.run

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class RunNotFoundException : RuntimeException("Run not found")
