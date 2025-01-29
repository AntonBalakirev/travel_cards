package org.example.travelcards.model.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.util.*

data class CardCreateRequest(
    @NotBlank(message = "Title is mandatory")
    @Size(max = 50, message = "Title must not exceed 50 characters")
    val title: String,

    @Size(max = 200, message = "Description must not exceed 200 characters")
    val description: String?,

    @Pattern(
        regexp = "^(https?://)?(www\\.)?google\\.com/maps/.*$",
        message = "Map link must be a valid Google Maps URL"
    )
    val mapLink: String?,

    @NotNull(message = "Travel ID is mandatory")
    val travelId: UUID
)