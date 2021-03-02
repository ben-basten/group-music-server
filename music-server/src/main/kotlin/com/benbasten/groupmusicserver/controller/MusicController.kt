package com.benbasten.groupmusicserver.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.MimeType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody
import org.springframework.util.ResourceUtils
import java.io.File
import java.io.OutputStream
import java.nio.file.Files


@RestController
@RequestMapping("/api/gms")
class MusicController {

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello World!"
    }

    @GetMapping("/tracks")
    fun getMusicListings(): List<String> {
        return listOf("The Nights", "Heaven", "Waiting for Love")
    }

    @GetMapping("/current/track")
    fun getCurrentTrack(): ResponseEntity<StreamingResponseBody> {
        val fileName = "the-nights.mp3"
        val file: File = ResourceUtils.getFile("classpath:static/$fileName")
        val responseBody = StreamingResponseBody { outputStream: OutputStream -> Files.copy(file.toPath(), outputStream) }
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Downloaded_$fileName")
            .contentType(MediaType.asMediaType(MimeType("audio", "mpeg")))
            .body(responseBody)
    }
}