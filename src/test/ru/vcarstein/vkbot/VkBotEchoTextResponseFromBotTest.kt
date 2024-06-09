package ru.vcarstein.vkbot

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.vcarstein.vkbot.service.VkBotService

@SpringBootTest
class VkBotEchoTextResponseFromBotTest {

    @Autowired
    private lateinit var vkBotService: VkBotService

    @Test
    fun testTextResponseFromBot() {
        val textFromUser = "Привет"
        val expectedTextReplyFromBot = "Вы сказали: $textFromUser"
        val actualTextReplyFromBot: String = vkBotService.sendMessage(12345, textFromUser)
        assertEquals(expectedTextReplyFromBot, actualTextReplyFromBot)
    }
}