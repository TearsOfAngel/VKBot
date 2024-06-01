package ru.vcarstein.vkbot.service.impl

import org.springframework.stereotype.Component
import ru.vcarstein.vkbot.data.VkMessage
import ru.vcarstein.vkbot.service.MessageHandler
import ru.vcarstein.vkbot.message.MessageType
import ru.vcarstein.vkbot.service.VkBotService

@Component
class NewMessageHandler(private val vkBotService: VkBotService) : MessageHandler {

    override fun getType(): MessageType {
        return MessageType.MESSAGE_NEW
    }

    override fun processMessage(message: VkMessage): String {
            vkBotService.handleIncomingMessage(message)
            return "ok"
        }
    }