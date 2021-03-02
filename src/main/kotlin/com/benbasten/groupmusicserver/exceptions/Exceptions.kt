package com.benbasten.groupmusicserver.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

open class TooManyRoomsException : RuntimeException()

@RestControllerAdvice
class Exceptions {

    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(TooManyRoomsException::class)
    fun tooManyRooms(ex: TooManyRoomsException): ExceptionResponse {
        return ExceptionResponse(HttpStatus.FORBIDDEN, "Maximum number of rooms reached.")
    }
}

class ExceptionResponse(var status: HttpStatus?  = null, var message: String? = null)