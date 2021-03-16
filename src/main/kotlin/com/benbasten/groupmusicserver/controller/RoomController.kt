package com.benbasten.groupmusicserver.controller

import com.benbasten.groupmusicserver.model.Track
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

@RestController
@RequestMapping("/api/gms/room")
class RoomController(val roomService: RoomService) {

    @GetMapping("/now-playing")
    fun getRoomNowPlaying(@RequestParam("roomId") roomId: Int): ResponseEntity<StreamingResponseBody> {
        val file: File = roomService.getCurrentTrack(roomId) ?: return ResponseEntity.noContent().build()
        val responseBody = StreamingResponseBody { outputStream: OutputStream -> Files.copy(file.toPath(), outputStream) }
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=${file.name}")
            .header("Cache-Control", "no-store")
            .contentType(MediaType.asMediaType(MimeType("audio", "mpeg")))
            .body(responseBody)
    }

    @GetMapping("/queue")
    fun getRoomQueue(@RequestHeader("roomId") roomId: Int): List<Track> {
        return roomService.getQueue(roomId)
    }

    @PostMapping("/queue/add")
    fun addToQueue(@RequestBody trackId: Int, @RequestHeader("roomId") roomId: Int): List<Track> {
        return roomService.addToQueue(roomId, trackId)
    }

    @PostMapping("/next-track")
    fun nextTrack(@RequestHeader("roomId") roomId: Int): List<Track> {
        return roomService.nextTrack(roomId)
    }
}