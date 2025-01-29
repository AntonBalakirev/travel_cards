package org.example.travelcards.service

import org.example.travelcards.model.dto.CardCreateRequest
import org.example.travelcards.model.dto.CardUpdateRequest
import org.example.travelcards.model.entity.Card
import org.example.travelcards.repository.CardRepository
import org.example.travelcards.repository.TravelRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class CardService(
    private val cardRepository: CardRepository,
    private val travelRepository: TravelRepository
) {
    fun createCard(request: CardCreateRequest): Card {
        val travel = travelRepository
            .findById(request.travelId)
            .orElseThrow { IllegalArgumentException("Travel not found for id: ${request.travelId}") }
        return Card(
            title = request.title,
            description = request.description,
            mapLink = request.mapLink,
            travel = travel
        )
    }

    fun deleteCard(id: UUID) {
        if (cardRepository.existsById(id)) {
            cardRepository.deleteById(id)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Card with id $id not found.")
        }
    }

    fun updateCard(request: CardUpdateRequest): Card {
        val existingCard = cardRepository.findById(request.id)
            .orElseThrow {
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "Card with id $request.id not found.")
            }

        return existingCard.apply {
            request.title.let { this.title = it }
            request.description.let { this.description = it }
            request.mapLink.let { this.mapLink = it }
            request.travelId.let {
                this.travel = travelRepository.findById(it).orElseThrow {
                    IllegalArgumentException("Travel id attached to Card not found: ${request.travelId}")
                }
            }
        }
    }
}