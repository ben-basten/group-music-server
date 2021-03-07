package com.benbasten.groupmusicserver.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.File
import kotlin.collections.ArrayDeque

@JsonIgnoreProperties(value = ["currentTrack"])
class Room(private val roomId: Int) {
    private var clientCount = 1
    private var queue: ArrayDeque<Track> = ArrayDeque()

    fun addUserToRoom() = clientCount++

    fun getCurrentTrack(): File? {
        return queue.firstOrNull()?.resource?.file
    }

    fun getClientCount(): Int = clientCount

    fun addToQueue(track: Track) = queue.addLast(track)

    fun getQueue(): List<Track> = queue.toList()

    companion object {
        fun generateRoomId(): Int {
            return (Math.random() * 9000).toInt() + 1000
        }
    }
}