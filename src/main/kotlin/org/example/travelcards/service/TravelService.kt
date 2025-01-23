package org.example.travelcards.service

import org.example.travelcards.repository.TravelRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

@Service
class TravelService(
    private val travelRepository: TravelRepository
) {
    fun deleteTravelById(id: UUID) {
        if (travelRepository.existsById(id)) {
            travelRepository.deleteById(id)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Travel with id $id not found.")
        }
    }
}