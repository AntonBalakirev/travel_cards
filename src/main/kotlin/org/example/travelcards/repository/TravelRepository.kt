package org.example.travelcards.repository

import org.example.travelcards.model.entity.Travel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface TravelRepository : JpaRepository<Travel, UUID> {

}