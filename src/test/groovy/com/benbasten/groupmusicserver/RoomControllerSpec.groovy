package com.benbasten.groupmusicserver

import com.benbasten.groupmusicserver.controller.RoomController
import com.benbasten.groupmusicserver.service.RoomService
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class RoomControllerSpec extends Specification {

    private prefix = "/api/gms/room"

    private RoomController roomController
    private RoomService roomService = Mock()
    private MockMvc mockMvc

    void setup() {
        roomController = new RoomController(roomService)
        mockMvc = standaloneSetup(roomController).build()
    }

    def 'get now playing - empty queue' () {
        when:
        mockMvc
            .perform(get("$prefix/now-playing")
                .param("roomId", "1415"))
            .andExpect(status().isNoContent())

        then:
        1 * roomService.getCurrentTrack(1415)
        0 * _
    }

    def 'get now playing - song returned' () {
        setup:
        File testFile = Mock()

        when:
        mockMvc
            .perform(get("$prefix/now-playing")
                .param("roomId", "1415"))
            .andExpect(status().isOk())
            .andExpect(header().string("cache-control", "no-store"))
            .andExpect(header().string("content-type", "audio/mpeg"))
            .andExpect(header().string("content-disposition", "inline; filename=song.mp3"))

        then:
        1 * roomService.getCurrentTrack(1415) >> testFile
        1 * testFile.toPath()
        1 * testFile.getName() >> "song.mp3"
        0 * _
    }

    def 'get room queue' () {
        when:
        mockMvc
                .perform(get("$prefix/queue")
                    .header("roomId", "5678"))
                .andExpect(status().isOk())

        then:
        1 * roomService.getQueue(5678)
        0 * _
    }

    def 'add to queue' () {
        when:
        mockMvc
                .perform(post("$prefix/queue/add")
                    .content("2") // track id in body
                    .contentType("application/json")
                    .header("roomId", "9101"))
                .andExpect(status().isOk())

        then:
        1 * roomService.addToQueue(9101, 2)
        0 * _
    }

    def 'next track' () {
        when:
        mockMvc
                .perform(post("$prefix/next-track")
                    .header("roomId", "1121"))
                .andExpect(status().isOk())

        then:
        1 * roomService.nextTrack(1121)
        0 * _
    }
}
