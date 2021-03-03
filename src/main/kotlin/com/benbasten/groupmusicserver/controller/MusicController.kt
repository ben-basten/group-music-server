package com.benbasten.groupmusicserver.controller

import com.benbasten.groupmusicserver.model.Track
import com.benbasten.groupmusicserver.service.MusicService
import com.benbasten.groupmusicserver.service.RoomService
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.MimeType
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody
import java.io.File
import java.io.OutputStream
import java.nio.file.Files
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/gms")
class MusicController(private val musicService: MusicService, private val roomService: RoomService) {

    @GetMapping("/hello")
    fun hello(): String {
        return "${musicService.hello()}, today is ${LocalDateTime.now()}"
    }

    @GetMapping("/tracks")
    fun getMusicListings(): List<Track> {
        return musicService.getTrackListings()
    }

    @GetMapping("/room/now-playing")
    fun getRoomNowPlaying(@RequestHeader("roomId") roomId: Int): ResponseEntity<StreamingResponseBody> {
        val file: File = roomService.getCurrentTrackForRoom(roomId)
        val responseBody = StreamingResponseBody { outputStream: OutputStream -> Files.copy(file.toPath(), outputStream) }
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=${file.name}")
            .contentType(MediaType.asMediaType(MimeType("audio", "mpeg")))
            .body(responseBody)
    }

    @GetMapping("/room/queue")
    fun getRoomQueue(@RequestHeader("roomId") roomId: Int): List<String> {
        return roomService.getQueueForRoom(roomId)
    }

    @PostMapping("/create")
    fun createRoom(): Int {
        return roomService.createRoom()
    }
}