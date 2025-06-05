package com.appname.backend.plugin

import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.request.*
import org.slf4j.LoggerFactory
import org.slf4j.event.Level

fun Application.configureCallLogging() {
    val accessLogger = LoggerFactory.getLogger("HTTP")
    
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
        disableDefaultColors()
        format { call ->
            val ip = call.request.origin.remoteHost
            val status = call.response.status()?.value ?: "-"
            val method = call.request.httpMethod.value
            val uri = call.request.uri
            "[$ip] $status $method $uri"
        }
        logger = accessLogger
    }
}
