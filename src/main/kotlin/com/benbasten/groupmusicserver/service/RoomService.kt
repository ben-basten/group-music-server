package com.benbasten.groupmusicserver.service

import com.benbasten.groupmusicserver.exceptions.RoomNotFoundException
import com.benbasten.groupmusicserver.exceptions.TooManyRoomsException
import com.benbasten.groupmusicserver.model.Room
import com.benbasten.groupmusicserver.model.Track
import org.springframework.stereotype.Component
import java.io.File

@Component
class RoomService(private val musicService: MusicService) {

    var rooms: HashMap<Int, Room> = HashMap()

    fun createRoom(): Room {
        if(rooms.size > 35) throw TooManyRoomsException()
        var id: Int
        do {
            id = Room.generateRoomId()
        } while(rooms.containsKey(id)) // guarantees no duplicates
        rooms[id] = Room(id)
        return rooms[id]!!
    }

    fun getRoom(roomId: Int): Room {
        return rooms[roomId] ?: throw RoomNotFoundException()
    }

    fun getRoomIdList(): List<Int> {
        return rooms.map { it.key }
    }

    fun getQueue(roomId: Int): List<Track> {
        return rooms[roomId]?.getQueue() ?: throw RoomNotFoundException()
    }

    fun addToQueue(roomId: Int, trackId: Int): List<Track> {
        if(!musicService.hasTrack(trackId)) return rooms[roomId]?.getQueue() ?: throw RoomNotFoundException()
        rooms[roomId]?.addToQueue(musicService.getTrack(trackId))
        return rooms[roomId]?.getQueue() ?: throw RoomNotFoundException()
    }

    fun getCurrentTrack(roomId: Int): File? {
        val room = rooms[roomId] ?: throw RoomNotFoundException()
        if(room.getCurrentTrack() != null) return room.getCurrentTrack()
        return null
    }

    fun nextTrack(roomId: Int): List<Track> {
        rooms[roomId]?.popQueue()
        return rooms[roomId]?.getQueue() ?: throw RoomNotFoundException()
    }
}