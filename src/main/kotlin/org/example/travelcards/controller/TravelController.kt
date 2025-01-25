package org.example.travelcards.controller

import jakarta.validation.Valid
import org.example.travelcards.mappers.toResponse
import org.example.travelcards.model.dto.TravelRequest
import org.example.travelcards.model.dto.TravelResponse
import org.example.travelcards.model.entity.Travel
import org.example.travelcards.repository.TravelRepository
import org.example.travelcards.service.TravelService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/travels")
class TravelController(
    private val travelService: TravelService,
    private val travelRepository: TravelRepository,
) {
    @GetMapping
    fun getAllTravels(): List<TravelResponse> =
        travelRepository.findAll().map { it.toResponse() }

    @GetMapping("/{id}")
    fun getTravel(@PathVariable id: UUID): TravelResponse =
        travelRepository.findById(id)
            .orElseThrow{IllegalArgumentException("Travel not found for id: $id")}
            .toResponse()

    @PostMapping
    fun createTravel(@Valid @RequestBody request: TravelRequest): TravelResponse {
        val travel = Travel(name = request.name)
        return travelRepository.save(travel)
            .toResponse()
    }

    @DeleteMapping("/{id}")
    fun deleteTravel(@PathVariable id: UUID):ResponseEntity<Void> {
        travelService.deleteTravelById(id)
        return ResponseEntity.noContent().build()
    }
}