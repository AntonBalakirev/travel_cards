package org.example.travelcards.repository

import org.example.travelcards.model.entity.Card
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CardRepository : JpaRepository<Card, UUID> {
    fun findByTravelId(travelId:UUID): List<Card>
}