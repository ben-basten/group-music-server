package com.benbasten.groupmusicserver.service

import com.benbasten.groupmusicserver.model.Track
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component

import org.springframework.core.io.support.ResourcePatternResolver
import java.lang.IllegalStateException


@Component
class MusicService(private val resourcePatternResolver: ResourcePatternResolver) {

    // music list only gets generated at first runtime
    private val trackList: HashMap<Int, Track> = makeTrackList()

    fun hello() = "hello"

    fun getTrackListings(): List<Track> {
        return trackList
            .map { it.value }
            .sortedBy { it.title }
    }

    fun getTrack(id: Int): Track? {
        return trackList[id]
    }

    fun hasTrack(id: Int): Boolean {
        return trackList[id] != null
    }

    private fun makeTrackList(): HashMap<Int, Track> {
        var resources: Array<Resource> = emptyArray()
        try {
            resources = resourcePatternResolver.getResources("classpath:music/*/*/*.mp3")
            resources += resourcePatternResolver.getResources("classpath:music/*/*.mp3")
            resources += resourcePatternResolver.getResources("classpath:music/*.mp3")
        } catch (ex: IllegalStateException) {
            System.err.println("An error occurred getting the mp3 resources: ${ex.message}")
            System.err.println(ex.stackTrace)
        }
        val tracks: HashMap<Int, Track> = HashMap()
        resources.mapIndexed { index, resource -> tracks.put(index, Track(index, resource))}
        return tracks
    }
}