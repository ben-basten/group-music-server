package com.benbasten.groupmusicserver.controller

import com.benbasten.groupmusicserver.service.RoomService
import org.springframework.stereotype.Controller

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate


@Controller
class WebSocketController(val simpMessagingTemplate: SimpMessagingTemplate, val roomService: RoomService) {

    // when a client adds something to the queue successfully and is connected to the websocket, notify other subscribers of change
    @MessageMapping("/queue/update") // incoming endpoint that is prefixed by /api/gms/ws
    fun notifyQueueChangeToRoom(roomId: Int) {
        // sends to all clients that are subscribed to this topic
        simpMessagingTemplate.convertAndSend("/topic/room/${roomId}/queue", roomService.getQueue(roomId))
    }
}