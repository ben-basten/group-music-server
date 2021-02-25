package com.benbasten.groupmusicserver.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MusicController {

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello World!"
    }
}