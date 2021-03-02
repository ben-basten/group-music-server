package com.benbasten.groupmusicserver.service

import org.springframework.core.io.Resource
import org.springframework.stereotype.Component

import org.springframework.core.io.support.ResourcePatternResolver
import java.io.File


@Component
class MusicService(private val resourcePatternResolver: ResourcePatternResolver) {

    // music list only gets generated at first runtime
    private val musicList: Array<Resource> = getMusicList()

    fun hello() = "hello"

    fun getFileListings(): List<String> {
        return musicList.map {it.filename!!}
    }

    fun getCurrentSong(): File {
        return musicList[0].file
    }

    private fun getMusicList(): Array<Resource> {
        var list = resourcePatternResolver.getResources("classpath:music/*/*.mp3")
        list += resourcePatternResolver.getResources("classpath:music/*.mp3")
        return list
    }
}