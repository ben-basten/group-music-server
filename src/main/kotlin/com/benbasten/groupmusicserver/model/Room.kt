package com.benbasten.groupmusicserver.model

import kotlin.collections.ArrayDeque

class Room(private val roomId: Int) {
    private var clientCount = 1
    private var queue: ArrayDeque<Int> = ArrayDeque()

    fun getCurrentTrack(): Int? {
        return queue.firstOrNull()
    }

    fun addToQueue(trackId: Int) = queue.addLast(trackId)

    companion object {
        fun generateRoomId(): Int {
            return (Math.random() * 9000).toInt() + 1000
        }
    }
}