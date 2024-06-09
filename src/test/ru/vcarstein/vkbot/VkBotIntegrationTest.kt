package ru.vcarstein.vkbot

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import ru.vcarstein.vkbot.data.VkMessage
import ru.vcarstein.vkbot.data.VkMessageObject
import ru.vcarstein.vkbot.data.VkObject

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VkBotIntegrationTest(
    @Value("\${vk.bot.confirmation}")
    private val expectedConfirmationString: String
) {

    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @DisplayName("New message handling")
    @Test
    fun testHandleNewMessage() {
        val url = "http://localhost:$port/callback"
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val vkMessage = VkMessage(
            type = "message_new",
            groupId = 3124125,
            obj = VkObject(
                message = VkMessageObject(
                    id = 1,
                    fromId = 54321,
                    peerId = 12345,
                    text = "Привет",
                    date = 3123123
                )
            )
        )

        val request = HttpEntity(vkMessage, headers)
        val response = restTemplate.exchange(url, HttpMethod.POST, request, String::class.java)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals("ok", response.body)
    }

    @DisplayName("Confirmation message handling")
    @Test
    fun testConfirmMessageHandling() {
        val url = "http://localhost:$port/callback"
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val confirmMessage = VkMessage(
            type = "confirmation",
            groupId = 226103768
        )

        val request = HttpEntity(confirmMessage, headers)
        val response = restTemplate.exchange(url, HttpMethod.POST, request, String::class.java)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(expectedConfirmationString, response.body)
    }
}