package com.github.mikemcgowan.runnerz

import org.springframework.stereotype.Component

@Component
class WelcomeMessage {
    fun welcomeMessage(): String = "Welcome! Nice to have you here. Do behave."
}
