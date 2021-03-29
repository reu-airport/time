package ru.reu.time.services

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import ru.reu.time.vo.TimeActions
import ru.reu.time.vo.TimeVO
import java.time.Instant
import java.util.concurrent.CountDownLatch

@Service
class TimeService(
    private val rabbitTemplate: RabbitTemplate
) {

    private val log = LoggerFactory.getLogger(javaClass)
    private val latch = CountDownLatch(1)

    companion object {
        var currentFactor = 1L
        const val speed = 2L
        const val DELAY_MS = 1000L
        const val MAX_SPEED = 1024L
        const val MIN_SPEED = 2L
    }

    @Scheduled(fixedDelay = 1000)
    fun currentTime() {
        rabbitTemplate.convertAndSend(
            "time",
            TimeVO(
                Instant
                    .now()
                    .plusMillis(DELAY_MS * currentFactor),
                DELAY_MS * currentFactor
            ).also { log.info("Current time: $it and factor: $currentFactor") }
        )
        latch.countDown()
    }

    fun changeTime(timeAction: TimeActions) {
        when (timeAction) {
            TimeActions.DECREASE -> if (currentFactor > MIN_SPEED) currentFactor /= speed
            TimeActions.INCREASE -> if (currentFactor < MAX_SPEED) currentFactor *= speed
        }
        log.info("Action: ${timeAction.name}, speed: $speed, current factor: $currentFactor")
    }

}
