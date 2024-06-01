package ru.vcarstein.vkbot.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import ru.vcarstein.vkbot.data.VkMessage
import ru.vcarstein.vkbot.data.VkResponse

@Service
class VkBotService(
    private val restTemplate: RestTemplate,
    @Value("\${vk.api.token}")
    private val apiToken: String,
    @Value("\${vk.bot.url}")
    private val apiUrl: String
) {

    fun handleIncomingMessage(message: VkMessage) {
        message.obj?.message?.let {
            sendMessage(it.peerId, it.text)
        }
    }

    fun sendMessage(peerId: Int, text: String) {
        val response = VkResponse(peerId, text)
        val uri = UriComponentsBuilder.fromHttpUrl(apiUrl)
            .queryParam("peer_id", response.peerId)
            .queryParam("message", "Вы сказали: " + response.message)
            .queryParam("random_id", (0..1000000).random())
            .queryParam("access_token", apiToken)
            .queryParam("v", "5.236")
            .build()
            .toUri()

        restTemplate.postForObject(uri, null, String::class.java)
    }
}