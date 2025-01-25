package org.example.travelcards.model.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class TravelRequest(
    @NotBlank(message = "Travel name is mandatory")
    @Size(max = 50, message = "Travel name must not exceed 50 characters")
    val name: String
)

