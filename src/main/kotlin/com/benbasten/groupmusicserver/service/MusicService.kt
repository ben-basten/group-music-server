package com.benbasten.groupmusicserver.service

import org.springframework.core.io.Resource
import org.springframework.stereotype.Component

import org.springframework.core.io.support.ResourcePatternResolver


@Component
class MusicService(private val resourcePatternResolver: ResourcePatternResolver) {

    private var musicList: Array<Resource>? = null

    fun hello() = "hello"

    fun getFileListings(): List<String> {
        if(musicList.isNullOrEmpty()) musicList = resourcePatternResolver.getResources("classpath:music/*/*.mp3")
        return musicList?.map {it.filename!!} ?: emptyList()
    }
}