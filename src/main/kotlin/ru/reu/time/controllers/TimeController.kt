package ru.reu.time.controllers

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import ru.reu.time.services.TimeService
import ru.reu.time.vo.TimeRq
import ru.reu.time.vo.TimeVO

@RestController
@RequestMapping("api/v1/time")
class TimeController(
    private val timeService: TimeService
) {

    @GetMapping
    fun getTime(): Mono<TimeVO> = Mono.just(timeService.currentTime())

    @PostMapping
    fun changeTime(@RequestBody timeRq: TimeRq): Mono<*> = Mono.just(timeService.changeTime(timeRq.action))

}
