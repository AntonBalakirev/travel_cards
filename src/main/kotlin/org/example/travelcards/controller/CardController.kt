package org.example.travelcards.controller

import jakarta.validation.Valid
import org.example.travelcards.mappers.toResponse
import org.example.travelcards.model.dto.CardRequest
import org.example.travelcards.model.dto.CardResponse
import org.example.travelcards.repository.CardRepository
import org.example.travelcards.service.CardService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/cards")
class CardController(
    private val cardService: CardService,
    private val cardRepository: CardRepository,
) {
    @GetMapping
    fun getAllCards(): List<CardResponse> =
        cardRepository.findAll().map { it.toResponse() }

    @PostMapping
    fun createCard(@Valid @RequestBody request: CardRequest): CardResponse {
        val card = cardService.createCard(request)
        return cardRepository.save(card).toResponse()
    }

    @GetMapping("/{id}")
    fun getCard(@PathVariable id: UUID): CardResponse =
        cardRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Travel not found for id: $id") }
            .toResponse()

    @DeleteMapping("/{id}")
    fun deleteCard(@PathVariable id: UUID): ResponseEntity<Void> {
        cardService.deleteCard(id)
        return ResponseEntity.noContent().build()
    }
}