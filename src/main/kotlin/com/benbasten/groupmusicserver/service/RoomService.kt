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

    fun createRoom(): Int {
        if(rooms.size > 5) throw TooManyRoomsException()
        var id: Int
        do {
            id = Room.generateRoomId()
        } while(rooms.containsKey(id)) // guarantees no duplicates
        rooms[id] = Room(id)
        return id
    }

    fun getQueueForRoom(roomId: Int): List<Track> {
        return rooms[roomId]?.getQueue() ?: throw RoomNotFoundException()
    }

    fun getCurrentTrackForRoom(roomId: Int): File? {
        val room = rooms[roomId] ?: throw RoomNotFoundException()
        if(room.getCurrentTrack() != null) return room.getCurrentTrack()
        return null
    }

    fun addToQueue(roomId: Int, trackId: Int): Boolean {
        if(!musicService.hasTrack(trackId)) return false
        rooms[roomId]?.addToQueue(musicService.getTrack(trackId)!!) ?: throw RoomNotFoundException()
        return true
    }
}