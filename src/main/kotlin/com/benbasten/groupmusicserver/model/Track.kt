package com.benbasten.groupmusicserver.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.core.io.Resource

@JsonIgnoreProperties(value = ["resource"])
class Track(var resource: Resource, val id: Int) {
    val prettyName: String = resource.filename!!.substringBefore('.')
}