package org.example.travelcards.controller

import org.example.travelcards.utils.TestUtils
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
class TravelControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var testUtils: TestUtils

    @Test
    fun `should delete travel and return no content`() {
        val travelId = testUtils.createTestTravel("Switzerland")
        mockMvc.perform(MockMvcRequestBuilders.delete("/travels/$travelId"))
            .andExpect(status().isNoContent)
    }

    @Test
    fun `should return 404 when deleting non-existing travel`() {
        val nonExistingTravelId = UUID.randomUUID()
        mockMvc.perform(MockMvcRequestBuilders.delete("/travels/$nonExistingTravelId"))
            .andExpect(status().isNotFound)
            .andExpect(content().string("Travel with id $nonExistingTravelId not found."))
    }
}