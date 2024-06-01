package ru.vcarstein.vkbot.data

data class VkResponse(
    val peerId: Int,
    val message: String,
    val randomId: Int = (0..100000).random()
)
