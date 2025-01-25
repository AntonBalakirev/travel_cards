package org.example.travelcards.model.dto

import java.util.*

data class CardResponse(
    val id: UUID,
    val title: String,
    val description: String?,
    val mapLink: String?,
    val travelId: UUID
)
