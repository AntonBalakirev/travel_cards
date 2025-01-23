package org.example.travelcards.service

import org.example.travelcards.repository.CardRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class CardService(
    private val cardRepository: CardRepository
) {
    fun deleteCard(id: UUID) {
        if (cardRepository.existsById(id)) {
            cardRepository.deleteById(id)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Card with id $id not found.")
        }
    }
}