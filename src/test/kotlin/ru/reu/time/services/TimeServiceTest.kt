package ru.reu.time.services

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.reu.time.vo.TimeActions
import ru.reu.time.vo.TimeVO
import java.lang.Thread.sleep
import java.time.Clock
import java.time.Instant
import java.time.Instant.now

internal class TimeServiceTest {

    private var now: () -> Long = { 100 }
    private lateinit var timeService: TimeService
    private lateinit var instant: Instant

    @BeforeEach
    fun setUp() {
        instant = mock {
            on { now() } doReturn 100
        }
        timeService = TimeService()
    }

    @Test
    fun currentTime() {
        Clock.systemDefaultZone().millis()
        val nowTime = timeService.currentTime()
        sleep(1000)
        val currentTime = timeService.currentTime()
        assertSame(nowTime.time.plusMillis(1000), currentTime.time)
    }

    @Test
    fun currentTimeWithIncrease() {
        Clock.systemDefaultZone().millis()
        val nowTime = timeService.currentTime()
        timeService.changeTime(TimeActions.INCREASE)
        sleep(1000)
        val currentTime = timeService.currentTime()
        assertEquals(nowTime.time.plusMillis(2000), currentTime.time)
    }

}
