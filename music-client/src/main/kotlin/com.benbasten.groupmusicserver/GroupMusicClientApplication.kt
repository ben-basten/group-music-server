package com.benbasten.groupmusicserver

import com.sun.jersey.api.client.Client
import com.sun.jersey.api.client.ClientResponse
import com.sun.jersey.api.client.WebResource
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.StackPane
import javafx.stage.Stage


class GroupMusicClientApplication : Application() {
    val APP_NAME = "Listening Party"

    override fun start(stage: Stage) {
        var output: String? = null

        try {
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