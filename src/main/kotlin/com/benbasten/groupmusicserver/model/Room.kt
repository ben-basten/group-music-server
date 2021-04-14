package com.benbasten.groupmusicserver.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.File
import kotlin.collections.ArrayDeque

@JsonIgnoreProperties(value = ["currentTrack"])
class Room(val roomId: Int) {
    private var queue: ArrayDeque<Track> = ArrayDeque()

    fun getCurrentTrack(): File? {
        return queue.firstOrNull()?.resource?.file
    }

    fun addToQueue(track: Track?) {
        // cannot add the same track twice to prevent caching in the frontend
        if(track != null && (queue.isEmpty() || queue.last().id != track.id)) {
            queue.addLast(track)
        }
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