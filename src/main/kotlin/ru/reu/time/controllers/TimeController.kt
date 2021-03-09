package ru.reu.time.controllers

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import ru.reu.time.services.TimeService
import ru.reu.time.vo.TimeRq
import ru.reu.time.vo.TimeVO
import java.time.Duration

@RestController
@RequestMapping("api/v1/time")
class TimeController(
    private val timeService: TimeService
) {

    @GetMapping(produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun currentTime(): Flux<TimeVO> = Flux.interval(Duration.ofMillis(1000), Schedulers.boundedElastic())
        .map { timeService.currentTime() }

    @PostMapping
    fun changeTime(@RequestBody timeRq: TimeRq): Mono<*> =
        Mono.just(timeService.changeTime(timeRq.action))
}
