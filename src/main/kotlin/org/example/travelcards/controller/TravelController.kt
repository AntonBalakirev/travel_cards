package org.example.travelcards.controller

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
    private val travelRepository: TravelRepository
) {
    @GetMapping
    fun getAllTravels(): List<Travel> = travelRepository.findAll()

    @PostMapping
    fun createTravel(@RequestBody travel: Travel): Travel = travelRepository.save(travel)

    @GetMapping("/{id}")
    fun getTravel(@PathVariable id: UUID): Travel = travelRepository.findById(id).orElseThrow()

    @DeleteMapping("/{id}")
    fun deleteTravel(@PathVariable id: UUID):ResponseEntity<Void> {
        travelService.deleteTravelById(id)
        return ResponseEntity.noContent().build()
    }
}