package com.appname.backend

import arrow.continuations.SuspendApp
import arrow.continuations.ktor.server
import arrow.core.raise.result
import arrow.fx.coroutines.resourceScope
import com.appname.backend.application.port.ApiKeyValidator
import com.appname.backend.infrastructure.auth.InMemoryApiKeyValidator
import com.appname.backend.plugin.*
import io.ktor.server.application.*
import io.ktor.server.netty.*
import kotlinx.coroutines.awaitCancellation
import org.slf4j.LoggerFactory
import java.util.*

internal val serverConfig = applicationConfig().server // Needs to load before log
internal val log by lazy { LoggerFactory.getLogger("ApplicationKt") }

fun main(args: Array<String>) = SuspendApp {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"))

    result {
        resourceScope {
            server(
                factory = Netty,
                port = serverConfig.port,
                preWait = serverConfig.preWait,
                module = applicationModule()
            )

            awaitCancellation()
        }
    }.onFailure { error ->
        log.error("Shutting down due to: ${error.stackTraceToString()}")
    }
}

fun applicationModule(validator: ApiKeyValidator = InMemoryApiKeyValidator()): Application.() -> Unit = {
    configureBearerApiKeyAuth(validator)
    configureSerialization()
    configureHttpExceptionHandling()
    configureHttpHeaders()
    configureCallLogging()
    configureRouting()
}

