package com.benbasten.groupmusicserver.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.File
import java.util.*
import kotlin.collections.ArrayDeque

@JsonIgnoreProperties(value = ["currentTrack"])
class Room(val roomId: Int) {
    private var queue: ArrayDeque<Track> = ArrayDeque()
    var lastAccessed: Date = Date()

    fun getCurrentTrack(): File? {
        updateTimestamp()
        return queue.firstOrNull()?.resource?.file
    }

    fun addToQueue(track: Track) {
        updateTimestamp()
        if(queue.isEmpty() || queue.last().id != track.id) queue.addLast(track)
    }

    fun getQueue(): List<Track> {
        updateTimestamp()
        return queue.toList()
    }

    fun popQueue() {
        updateTimestamp()
        if(queue.isNotEmpty()) queue.removeFirst()
    }

    fun updateTimestamp() {
        lastAccessed = Date()
    }

    companion object {
        fun generateRoomId(): Int {
            return (Math.random() * 9000).toInt() + 1000
        }
    }
}