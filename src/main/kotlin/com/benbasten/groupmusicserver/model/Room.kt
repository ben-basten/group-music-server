package com.benbasten.groupmusicserver.model

import kotlin.collections.ArrayDeque

class Room {
    private var clientCount = 1
    private var queue: ArrayDeque<Int> = ArrayDeque()

    fun generateRoomId(): Int {
        return (Math.random() * 9000).toInt() + 1000
    }

    fun getCurrentTrack(): Int? {
        return queue.firstOrNull()
    }

    fun addToQueue(trackId: Int) = queue.addLast(trackId)
}