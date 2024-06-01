package ru.vcarstein.vkbot.service.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import ru.vcarstein.vkbot.data.VkMessage
import ru.vcarstein.vkbot.service.MessageHandler
import ru.vcarstein.vkbot.message.MessageType

@Component
class ConfirmMessageHandler(
    @Value("\${vk.bot.confirmation}")
    private val confirmationString: String
) : MessageHandler {

    override fun getType(): MessageType {
        return MessageType.CONFIRMATION
    }

    override fun processMessage(message: VkMessage): String {
        return "72aba490"
    }
}