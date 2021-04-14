package com.benbasten.groupmusicserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class GroupMusicServerApplication

fun main(args: Array<String>) {
	runApplication<GroupMusicServerApplication>(*args)
}
