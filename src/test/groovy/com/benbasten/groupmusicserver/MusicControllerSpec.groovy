package com.benbasten.groupmusicserver

import com.benbasten.groupmusicserver.controller.MusicController
import com.benbasten.groupmusicserver.service.MusicService
import com.benbasten.groupmusicserver.service.RoomService
import org.springframework.test.web.servlet.MockMvc
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import spock.lang.Specification

class MusicControllerSpec extends Specification {

    private prefix = "/api/gms"

    private MusicController musicController
    private MusicService musicService = Mock()
    private RoomService roomService = Mock()
    private MockMvc mockMvc

    void setup () {
        musicController = new MusicController(musicService, roomService)
        mockMvc = standaloneSetup(musicController).build()
    }

    def 'get hello endpoint' () {
        when:
        def result = mockMvc
                .perform(get("$prefix/hello"))
                .andExpect(status().isOk())
                .andReturn().response

        then:
        1 * musicService.hello() >> "howdy"
        0 * _

        and:
        result.contentAsString.contains("howdy, today is")
    }

    def 'get music listings endpoint' () {
        when:
        mockMvc
                .perform(get("$prefix/tracks"))
                .andExpect(status().isOk())

        then:
        1 * musicService.getTrackListings()
        0 * _
    }

    def 'create room endpoint' () {
        when:
        mockMvc
                .perform(post("$prefix/create"))
                .andExpect(status().isOk())

        then:
        1 * roomService.createRoom()
        0 * _
    }

    def 'join room endpoint' () {
        when:
        mockMvc
                .perform(post("$prefix/join")
                .content("1234")
                .contentType("application/json"))
                .andExpect(status().isOk())

        then:
        1 * roomService.getRoom(1234)
        0 * _
    }

    def 'get rooms list endpoint' () {
        when:
        mockMvc
                .perform(get("$prefix/rooms"))
                .andExpect(status().isOk())

        then:
        1 * roomService.getRoomIdList()
        0 * _
    }
}