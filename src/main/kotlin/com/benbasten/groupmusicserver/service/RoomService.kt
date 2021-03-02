package com.benbasten.groupmusicserver.service

import com.benbasten.groupmusicserver.model.Room
import org.springframework.stereotype.Component
import java.io.File

@Component
class RoomService(private val musicService: MusicService) {

    var rooms: List<Room> = emptyList()

    fun getQueueForRoom(roomId: Int): List<String> {
        return listOf("Wildflowers", "Runnin' Down a Dream", "Honeybee")
    }

    fun getCurrentTrackForRoom(roomId: Int): File {
        return musicService.getTrack(13)
    }
}