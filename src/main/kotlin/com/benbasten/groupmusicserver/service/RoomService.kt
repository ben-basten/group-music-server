package com.benbasten.groupmusicserver.service

import com.benbasten.groupmusicserver.exceptions.RoomNotFoundException
import com.benbasten.groupmusicserver.exceptions.TooManyRoomsException
import com.benbasten.groupmusicserver.model.Room
import org.springframework.stereotype.Component
import java.io.File

@Component
class RoomService(private val musicService: MusicService) {

    var rooms: HashMap<Int, Room> = HashMap()

    fun createRoom(): Int {
        if(rooms.size > 5) throw TooManyRoomsException()
        val newRoom = Room()
        var id: Int
        do {
            id = newRoom.generateRoomId()
        } while(rooms.containsKey(id)) // guarantees no duplicates
        rooms[id] = newRoom
        return id
    }

    fun getQueueForRoom(roomId: Int): List<String> {
        return listOf("Wildflowers", "Runnin' Down a Dream", "Honeybee")
    }

    fun getCurrentTrackForRoom(roomId: Int): File {
        val trackId = rooms[roomId]?.getCurrentTrack() ?: throw RoomNotFoundException()
        return musicService.getTrack(trackId)
    }
}