package com.benbasten.groupmusicserver.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.StompEndpointRegistry

import org.springframework.messaging.simp.config.MessageBrokerRegistry

import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker


@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer {
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        // endpoint for the client to initially connect to the server
        registry.addEndpoint("/api/gms/ws-connect").setAllowedOrigins("*")
    }

    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        // prefix for incoming messages with @MessageMapping annotation
        config.setApplicationDestinationPrefixes("/api/gms/ws")
        // prefix for outgoing messages
        config.enableSimpleBroker("/topic")
    }
}