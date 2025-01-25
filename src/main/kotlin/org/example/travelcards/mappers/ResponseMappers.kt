package org.example.travelcards.mappers

import org.example.travelcards.model.dto.CardResponse
import org.example.travelcards.model.dto.TravelResponse
import org.example.travelcards.model.entity.Card
import org.example.travelcards.model.entity.Travel

fun Travel.toResponse() = TravelResponse(
    id = this.id!!,
    name = this.name,
    cards = this.cards.map { it.toResponse() }
)

fun Card.toResponse() = CardResponse(
    id = this.id!!,
    title = this.title,
    description = this.description,
    mapLink = this.mapLink,
    travelId = this.travel!!.id!!
)