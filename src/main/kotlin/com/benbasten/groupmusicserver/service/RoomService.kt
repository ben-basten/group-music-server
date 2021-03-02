package com.benbasten.groupmusicserver.service

import org.springframework.stereotype.Component

@Component
class RoomService {

    fun getQueueForRoom(roomId: Int): List<String> {
        return listOf("Wildflowers", "Runnin' Down a Dream", "Honeybee")
    }
}