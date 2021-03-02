package com.benbasten.groupmusicserver.service

import org.springframework.stereotype.Component

@Component
class MusicService {

    fun hello() = "hello"

    fun getFileListings(): List<String> {
        return listOf("The Nights", "Heaven", "Waiting for Love")
    }
}