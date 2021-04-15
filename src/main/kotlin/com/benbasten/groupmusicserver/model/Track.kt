package com.benbasten.groupmusicserver.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.mpatric.mp3agic.Mp3File
import org.springframework.core.io.Resource
import java.lang.NullPointerException

@JsonIgnoreProperties(value = ["resource"])
class Track(val id: Int, var resource: Resource) {
    private val mp3File: Mp3File = Mp3File(resource.file)
    val title: String = getTrackTitle()
    val artist: String = getTrackArtist()
    val album: String = getTrackAlbum()
    val art: ByteArray? = getAlbumArt()

    private fun getTrackTitle(): String {
        return try {
            mp3File.id3v2Tag.title
        } catch (ex: NullPointerException) {
            // use filename without extension if song title is not in metadata
            resource.filename!!.substringBefore('.')
        }
    }

    private fun getTrackArtist(): String {
        return try {
            mp3File.id3v2Tag.albumArtist
        } catch (ex: NullPointerException) {
            "Unknown artist"
        }
    }

    private fun getTrackAlbum(): String {
        return try {
            mp3File.id3v2Tag.album
        } catch (ex: NullPointerException) {
            "Unknown album"
        }
    }

    private fun getAlbumArt(): ByteArray? {
        return try {
            mp3File.id3v2Tag.albumImage
        } catch (ex: NullPointerException) {
            null
        }
    }
}