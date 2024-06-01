package ru.vcarstein.vkbot.message

import ru.vcarstein.vkbot.exceptions.UnsupportedMessageType
import java.util.*

enum class MessageType(private val type: String) {
    CONFIRMATION("confirmation"),
    MESSAGE_NEW("message_new"),
    MESSAGE_TYPING_STATE("message_typing_state");

    companion object {
        fun getMessageType(messageType: String): MessageType {
            return entries.firstOrNull() { m ->
                m.type == messageType.lowercase(Locale.getDefault())
            } ?: throw UnsupportedMessageType("Неподдерживаемый тип сообщений: $messageType")
        }
    }
}