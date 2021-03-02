package com.benbasten.groupmusicserver.service

import org.springframework.core.io.Resource
import org.springframework.stereotype.Component

import org.springframework.core.io.support.ResourcePatternResolver
import java.io.File
import java.io.FileNotFoundException


@Component
class MusicService(private val resourcePatternResolver: ResourcePatternResolver) {

    // music list only gets generated at first runtime
    private val musicList: Array<Resource> = getMusicList()

    fun hello() = "hello"

    fun getTrackListings(): List<String> {
        return musicList.map {it.filename!!}
    }

    fun getTrack(id: Int): File {
        if(id < 0 || id > musicList.size) throw FileNotFoundException()
        return musicList[id].file
    }

    private fun getMusicList(): Array<Resource> {
        var list = resourcePatternResolver.getResources("classpath:music/*/*.mp3")
        list += resourcePatternResolver.getResources("classpath:music/*.mp3")
        return list
    }
}