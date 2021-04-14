package com.benbasten.groupmusicserver.service

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ScheduledTasks(private val roomService: RoomService) {

    @Scheduled(fixedRate = 5000)
    fun clearUnusedRooms() {
        println("cron ran")
    }
}