package com.benbasten.groupmusicserver

import com.benbasten.groupmusicserver.controller.RoomController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RoomControllerIntegrationSpec extends Specification {

    @Autowired
    RoomController roomController

    def 'gets wired' () {
        expect:
        roomController
        roomController.roomService
    }
}
