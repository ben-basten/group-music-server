package com.benbasten.groupmusicserver

import com.benbasten.groupmusicserver.controller.MusicController
import com.benbasten.groupmusicserver.service.MusicService
import com.benbasten.groupmusicserver.service.RoomService
import org.springframework.test.web.servlet.MockMvc
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import spock.lang.Specification

class MusicControllerSpec extends Specification {

    private MusicController musicController
    private MusicService musicService = Mock()
    private RoomService roomService = Mock()
    private MockMvc mockMvc

    void setup () {
        musicController = new MusicController(musicService, roomService)
        mockMvc = standaloneSetup(musicController).build()
    }

    def 'hello endpoint' () {
        when:
        def result = mockMvc
                .perform(get("/api/gms/hello"))
                .andExpect(status().isOk())
                .andReturn().response

        then:
        1 * musicService.hello() >> "howdy"
        0 * _

        and:
        result.contentAsString.contains("howdy, today is")
    }
}