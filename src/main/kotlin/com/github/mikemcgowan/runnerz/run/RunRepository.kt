package com.github.mikemcgowan.runnerz.run

import org.springframework.data.repository.ListCrudRepository

interface RunRepository : ListCrudRepository<Run, Int>
