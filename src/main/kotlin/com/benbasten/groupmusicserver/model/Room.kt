package com.benbasten.groupmusicserver.model

class Room {
    var roomId: Int? = null
    var clientCount = 0
    var queue: List<Int> = emptyList()
}