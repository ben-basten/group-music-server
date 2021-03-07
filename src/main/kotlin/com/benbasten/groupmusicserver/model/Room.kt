package com.benbasten.groupmusicserver.model

import java.io.File
import kotlin.collections.ArrayDeque

class Room(private val roomId: Int) {
    private var clientCount = 1
    private var queue: ArrayDeque<Track> = ArrayDeque()

    fun getCurrentTrack(): File? {
        return queue.firstOrNull()?.resource?.file
    }

    fun addToQueue(track: Track) = queue.addLast(track)

    fun getQueue(): List<Track> = queue.toList()

    companion object {
        fun generateRoomId(): Int {
            return (Math.random() * 9000).toInt() + 1000
        }
    }
}