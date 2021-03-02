package com.benbasten.groupmusicserver.service

import org.springframework.core.io.Resource
import org.springframework.stereotype.Component

import org.springframework.core.io.support.ResourcePatternResolver


@Component
class MusicService(private val resourcePatternResolver: ResourcePatternResolver) {

    fun hello() = "hello"

    fun getFileListings(): List<String> {
        val resources: Array<Resource> = resourcePatternResolver.getResources("classpath:music/*.mp3")
        return resources.filterNot{it.filename.isNullOrEmpty()}.map {it.filename!!}
    }
}