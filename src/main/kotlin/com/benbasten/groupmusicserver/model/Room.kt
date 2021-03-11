package com.benbasten.groupmusicserver.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.File
import kotlin.collections.ArrayDeque

@JsonIgnoreProperties(value = ["currentTrack"])
class Room(val roomId: Int) {
    private var clientCount = 0
    private var queue: ArrayDeque<Track> = ArrayDeque()

    fun incrementClientCount(): Int = ++clientCount

    fun getCurrentTrack(): File? {
        return queue.firstOrNull()?.resource?.file
    }

    fun addToQueue(track: Track) {
        if(queue.isEmpty() || queue.last().id != track.id) queue.addLast(track)
    }

    fun getQueue(): List<Track> = queue.toList()

    fun popQueue() {
        if(queue.isNotEmpty()) queue.removeFirst()
    }

    companion object {
        fun generateRoomId(): Int {
            return (Math.random() * 9000).toInt() + 1000
        }
    }
}