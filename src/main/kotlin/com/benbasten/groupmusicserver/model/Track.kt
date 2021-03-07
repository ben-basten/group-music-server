package com.benbasten.groupmusicserver.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.mpatric.mp3agic.Mp3File
import org.springframework.core.io.Resource

@JsonIgnoreProperties(value = ["resource"])
class Track(var resource: Resource, val id: Int) {
    private val mp3File: Mp3File = Mp3File(resource.file)
    val title: String = findTrackTitle()
    val artist: String = if(mp3File.hasId3v2Tag()) mp3File.id3v2Tag.albumArtist else "Unknown artist"
    val album: String = if(mp3File.hasId3v2Tag()) mp3File.id3v2Tag.album else "Unknown album"

    private fun findTrackTitle(): String {
        if(mp3File.hasId3v2Tag()) return mp3File.id3v2Tag.title
        // use filename without extension if song title is not in metadata
        return resource.filename!!.substringBefore('.')
    }
}