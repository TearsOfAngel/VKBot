package ru.vcarstein.vkbot.controller

import org.apache.logging.log4j.kotlin.Logging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.vcarstein.vkbot.data.VkMessage
import ru.vcarstein.vkbot.exceptions.UnsupportedMessageType
import ru.vcarstein.vkbot.service.MessageHandler
import ru.vcarstein.vkbot.service.MessageType


@RequestMapping("/callback")
@RestController
class VkController : Logging {

    @Autowired
    private lateinit var messageTypes: Map<MessageType, MessageHandler>

    @PostMapping
    fun handleCallback(@RequestBody message: VkMessage): String {
        var type: MessageType? = null

        try {
            type = MessageType.getMessageType(message.type)
        } catch (ex: UnsupportedMessageType) {
            logger.error("${ex.message}")
        }

        val msgHandler: MessageHandler? = messageTypes[type]

        return msgHandler?.processMessage(message) ?: "ok"
    }

}