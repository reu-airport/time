package ru.reu.time.controllers

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import ru.reu.time.services.TimeService

@WebFluxTest
internal class TimeControllerTest {

    private lateinit var timeService: TimeService

    @BeforeEach
    fun setUp() {

//        timeService = TimeService()

    }

    @Test
    fun currentTime() {
    }

    @Test
    fun changeTime() {
    }

}
