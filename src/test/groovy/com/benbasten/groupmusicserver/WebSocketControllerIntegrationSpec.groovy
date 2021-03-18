package com.benbasten.groupmusicserver

import com.benbasten.groupmusicserver.controller.WebSocketController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import spock.lang.Specification

import com.neovisionaries.ws.client.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebSocketControllerIntegrationSpec extends Specification {

    @Autowired
    WebSocketController webSocketController

    @LocalServerPort int port


    def openSocket() {
        new WebSocketFactory()
            .createSocket("http://localhost:$port/api/gms/ws-connect")
            .connect()
    }

    def 'gets wired' () {
        expect:
        webSocketController
        webSocketController.simpMessagingTemplate
        webSocketController.roomService
    }

    // reference: https://evojam.com/technology-blog/2020/5/18/the-secret-of-painless-websocket-tests-with-spock-framework
    def "socket opens"() {
        when:
        def socket = openSocket()

        then:
        socket.isOpen()

        cleanup:
        socket.disconnect(WebSocketCloseCode.NORMAL, null, 0)
    }
}
