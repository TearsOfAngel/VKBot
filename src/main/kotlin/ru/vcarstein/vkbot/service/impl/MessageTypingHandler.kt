package ru.vcarstein.vkbot.service.impl

import org.springframework.stereotype.Component
import ru.vcarstein.vkbot.data.VkMessage
import ru.vcarstein.vkbot.service.MessageHandler
import ru.vcarstein.vkbot.message.MessageType

@Component
class MessageTypingHandler : MessageHandler {

    override fun getType(): MessageType {
        return MessageType.MESSAGE_TYPING_STATE
    }

    override fun processMessage(message: VkMessage): String {
        return "ok"
    }
}