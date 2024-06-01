package ru.vcarstein.vkbot.data

import com.fasterxml.jackson.annotation.JsonProperty

data class VkMessageObject(
    val id: Int,
    val date: Int,
    @JsonProperty("peer_id") val peerId: Int,
    @JsonProperty("from_id") val fromId: Int,
    val text: String
)
