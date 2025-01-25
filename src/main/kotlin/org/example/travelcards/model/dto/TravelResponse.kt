package org.example.travelcards.model.dto

import java.util.*

data class TravelResponse(
    val id: UUID,
    val name: String,
    val cards: List<CardResponse> = emptyList()
)
