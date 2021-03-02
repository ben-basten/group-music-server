package com.benbasten.groupmusicserver

import com.sun.jersey.api.client.Client
import com.sun.jersey.api.client.ClientResponse
import com.sun.jersey.api.client.WebResource
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.net.http.HttpResponse.BodyHandlers








class GroupMusicClientApplication : Application() {
    val APP_NAME = "Listening Party"
    var client: HttpClient? = null

    override fun start(stage: Stage) {
        val javaVersion = System.getProperty("java.version")
        val javafxVersion = System.getProperty("javafx.version")

        var output: String? = null
        var response: HttpResponse<String>? = null
        try {
//            var request = HttpRequest.newBuilder(
//                URI.create("https://postman-echo.com/get"))
//                .GET()
//                .build()
//            response = client?.send(request, BodyHandlers.ofString())
            val client: Client = Client.create()

            val webResource: WebResource = client
                .resource("http://localhost:8080/api/gms/hello")

            val response: ClientResponse = webResource.accept("application/json")
                .get(ClientResponse::class.java)

            if (response.status != 200) {
                throw RuntimeException(
                    "Failed : HTTP error code : "
                            + response.status
                )
            }

            output = response.getEntity(String::class.java)

        } catch (e:Exception) {
            System.err.println("Something went wrong.")
        }

        val text = Label(output ?: "no dice")

        val scene = Scene(StackPane(text), 640.0, 480.0)
        stage.scene = scene
        stage.title = APP_NAME
        stage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(GroupMusicClientApplication::class.java, *args)
        }
    }
}