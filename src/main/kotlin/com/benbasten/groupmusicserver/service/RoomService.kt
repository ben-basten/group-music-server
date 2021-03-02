package com.benbasten.groupmusicserver.service

import com.benbasten.groupmusicserver.exceptions.TooManyRoomsException
import com.benbasten.groupmusicserver.model.Room
import org.springframework.stereotype.Component
import java.io.File

@Component
class RoomService(private val musicService: MusicService) {

    var rooms: ArrayList<Room> = ArrayList<Room>()

    fun createRoom(): Int {
        if(rooms.size > 5) throw TooManyRoomsException()
        val newRoom = Room()
        rooms.add(newRoom)
        return newRoom.roomId
    }

    fun getQueueForRoom(roomId: Int): List<String> {
        return listOf("Wildflowers", "Runnin' Down a Dream", "Honeybee")
    }

    fun getCurrentTrackForRoom(roomId: Int): File {
        return musicService.getTrack(13)
    }
}