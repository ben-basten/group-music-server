package com.benbasten.groupmusicserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GroupMusicServerApplication

fun main(args: Array<String>) {
	runApplication<GroupMusicServerApplication>(*args)
}
