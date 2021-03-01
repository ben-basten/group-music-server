package com.benbasten.groupmusicserver

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.StackPane
import javafx.stage.Stage




class GroupMusicClientApplication : Application() {
    override fun start(stage: Stage) {
        val javaVersion = System.getProperty("java.version")
        val javafxVersion = System.getProperty("javafx.version")
        val text = Label("Hello world, JavaFX $javafxVersion, running on Java $javaVersion.")
        val scene = Scene(StackPane(text), 640.0, 480.0)
        stage.scene = scene
        stage.title = "JavaFX Test"
        stage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(GroupMusicClientApplication::class.java, *args)
        }
    }
}