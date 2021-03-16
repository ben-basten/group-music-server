package com.benbasten.groupmusicserver

import com.benbasten.groupmusicserver.controller.MusicController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MusicControllerIntegrationSpec extends Specification {

    @Autowired
    MusicController musicController

    def 'gets wired' () {
        expect:
        musicController
        musicController.musicService
        musicController.roomService
    }
}
