package ru.vcarstein.vkbot.data

import com.fasterxml.jackson.annotation.JsonProperty

data class VkMessage(
    val type: String,
    @JsonProperty("object") val obj: VkObject? = null,
    @JsonProperty("group_id") val groupId: Long
)
