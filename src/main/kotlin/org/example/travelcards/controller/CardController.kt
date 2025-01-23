package org.example.travelcards.controller

import org.example.travelcards.model.dto.CardRequest
import org.example.travelcards.model.entity.Card
import org.example.travelcards.repository.CardRepository
import org.example.travelcards.repository.TravelRepository
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
    private val travelRepository: TravelRepository,
) {
    @GetMapping
    fun getAllCards(): List<Card> = cardRepository.findAll()

    @PostMapping
    fun createCard(@RequestBody request: CardRequest): Card {
        val travel = travelRepository
            .findById(request.travelId)
            .orElseThrow{IllegalArgumentException("Travel not found for id: ${request.travelId}")}
        val card = Card(
            title = request.title,
            description = request.description,
            mapLink = request.mapLink,
            travel = travel
        )
        return cardRepository.save(card)
    }

    @GetMapping("/{id}")
    fun getCard(id: UUID): Card = cardRepository.findById(id).orElseThrow()

    @DeleteMapping("/{id}")
    fun deleteCard(@PathVariable id: UUID): ResponseEntity<Void> {
        cardService.deleteCard(id)
        return ResponseEntity.noContent().build()
    }
}