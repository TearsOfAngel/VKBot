package ru.vcarstein.vkbot.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import ru.vcarstein.vkbot.service.MessageHandler
import ru.vcarstein.vkbot.service.MessageType

@Configuration
class BeanConfiguration {

    @Bean
    fun messageTypes(handlerBeans: Set<MessageHandler>): Map<MessageType, MessageHandler> {
        return handlerBeans.associateBy(
            {it.getType()}, {it}
        )
    }

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }
}
