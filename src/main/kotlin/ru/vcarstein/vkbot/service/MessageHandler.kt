package ru.vcarstein.vkbot.service

import ru.vcarstein.vkbot.data.VkMessage
import ru.vcarstein.vkbot.message.MessageType

interface MessageHandler {
    fun getType(): MessageType
    fun processMessage(message: VkMessage): String
}