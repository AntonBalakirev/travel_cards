package org.example.travelcards.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.server.ResponseStatusException

@ControllerAdvice
class GlobalExceptionHandler {

    private val logger: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(ex: ResponseStatusException): ResponseEntity<String> {
        logger.warn("ResponseStatusException occurred: ${ex.reason}", ex)
        return ResponseEntity(ex.reason, ex.statusCode)
    }

    @ExceptionHandler(Exception::class)
    fun handlerGenericException(ex: Exception): ResponseEntity<String> {
        logger.error("Unexpected exception occurred", ex)
        return ResponseEntity("Internal Server Error: ${ex.message}", HttpStatus.INTERNAL_SERVER_ERROR)
    }
}