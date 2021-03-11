package com.benbasten.groupmusicserver.controller

import com.benbasten.groupmusicserver.service.RoomService
import org.springframework.stereotype.Controller

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate


@Controller
class WebSocketController(private val simpMessagingTemplate: SimpMessagingTemplate, private val roomService: RoomService) {

    @MessageMapping("/hello")   // incoming endpoint that is prefixed by /api/gms/ws
    fun greeting(roomId: Int) {
        simpMessagingTemplate.convertAndSend("/topic/greetings", roomService.getQueueForRoom(roomId))
    }
}