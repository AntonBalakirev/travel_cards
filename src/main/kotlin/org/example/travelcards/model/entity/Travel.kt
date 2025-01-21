package org.example.travelcards.model.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "travels")
data class Travel(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,

    @Column(nullable = false)
    val name: String = "",

    @OneToMany(mappedBy = "travel", cascade = [CascadeType.ALL], orphanRemoval = true)
    val cards: List<Card> = mutableListOf()

)
