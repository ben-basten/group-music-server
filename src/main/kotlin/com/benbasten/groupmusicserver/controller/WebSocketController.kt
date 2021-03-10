package com.benbasten.groupmusicserver.controller

import org.springframework.stereotype.Controller

import org.springframework.messaging.handler.annotation.SendTo

import org.springframework.messaging.handler.annotation.MessageMapping
import java.lang.Exception


@Controller
class WebSocketController {

    @MessageMapping("/hello")   // incoming endpoint that is prefixed by /api/gms/ws
    @SendTo("/topic/greetings") // outgoing endpoint that clients subscribe to - must have /topic prefix
    @Throws(Exception::class)
    fun greeting(message: String): String {
        Thread.sleep(1000) // simulated delay
        return "Is this working?"
    }
}