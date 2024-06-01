package ru.vcarstein.vkbot.service

import ru.vcarstein.vkbot.data.VkMessage

interface MessageHandler {
    fun getType(): MessageType
    fun processMessage(message: VkMessage): String
}