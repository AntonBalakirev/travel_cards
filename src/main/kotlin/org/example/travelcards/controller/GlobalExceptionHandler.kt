package org.example.travelcards.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.server.ResponseStatusException

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(ex: ResponseStatusException): ResponseEntity<String> {
        return ResponseEntity(ex.reason, ex.statusCode)
    }

    @ExceptionHandler(Exception::class)
    fun handlerGenericException(ex: Exception): ResponseEntity<String> {
        return ResponseEntity("Internal Server Error: ${ex.message}", HttpStatus.INTERNAL_SERVER_ERROR)
    }
}