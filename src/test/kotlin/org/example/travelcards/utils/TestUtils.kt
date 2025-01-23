package org.example.travelcards.utils

import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@Component
class TestUtils(
    @Autowired private val mockMvc: MockMvc
) {
    fun createTestTravel(name: String): UUID {
        val result = mockMvc.perform(
            MockMvcRequestBuilders.post("/travels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                        {
                            "name":"$name"
                        }
                    """.trimIndent()
                )
        ).andExpect(status().isOk)
            .andReturn()

        val responseJson = result.response.contentAsString
        val travelId = JSONObject(responseJson).getString("id")
        return UUID.fromString(travelId)
    }

    fun createTestCard(title: String, travelId: UUID): UUID {
        val result = mockMvc.perform(
            MockMvcRequestBuilders.post("/cards")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                        {
                            "title":"$title",
                            "description":"This is a test task.",
                            "mapLink":"https://maps.google.com",
                            "travelId":"$travelId"
                        }
                    """.trimIndent()
                )
        ).andExpect(status().isOk)
            .andReturn()

        val responseJson = result.response.contentAsString
        val cardId = JSONObject(responseJson).getString("id")
        return UUID.fromString(cardId)
    }
}