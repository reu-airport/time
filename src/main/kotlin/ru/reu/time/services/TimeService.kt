package ru.reu.time.services

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.reu.time.vo.TimeActions
import ru.reu.time.vo.TimeVO
import java.time.Instant

@Service
class TimeService {

    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        var currentFactor = 1L
        const val speed = 2L
        const val DELAY_MS = 1000L
        const val MAX_SPEED = 1024L
        const val MIN_SPEED = 2L
    }

    fun currentTime(): TimeVO = TimeVO(
        Instant
            .now()
            .plusMillis(DELAY_MS * currentFactor)
    ).also { log.info("Current time: $it and factor: $currentFactor") }

    fun changeTime(timeAction: TimeActions) {
        when (timeAction) {
            TimeActions.DECREASE -> if (currentFactor > MIN_SPEED) currentFactor /= speed
            TimeActions.INCREASE -> if (currentFactor < MAX_SPEED) currentFactor *= speed
        }
        log.info("Action: ${timeAction.name}, speed: $speed, current factor: $currentFactor")
    }

}
