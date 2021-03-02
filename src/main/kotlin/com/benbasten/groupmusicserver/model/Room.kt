package com.benbasten.groupmusicserver.model

class Room {
    var roomId: Int = generateRoomId()
    private var clientCount = 1
    private var queue: List<Int> = emptyList()

    private fun generateRoomId(): Int {
        return (Math.random() * 9999).toInt() + 1000
    }
}