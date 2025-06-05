package com.appname.backend.plugin

import com.appname.backend.exception.ValidationException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import kotlinx.serialization.Serializable
import org.slf4j.LoggerFactory

fun Application.configureHttpExceptionHandling() {
    val log = LoggerFactory.getLogger("HttpExceptionHandling")

    install(StatusPages) {
        exception<ValidationException> { call, cause ->
            log.debug("Validation error: {}", cause.message)
            call.respond(
                HttpStatusCode.BadRequest,
                ErrorResponse(
                    error = "Validation failed",
                    details = cause.errors.joinToString("; ") { "${it.field}: ${it.message}" }
                )
            )
        }

        exception<Throwable> { call, cause ->
            log.error("Unhandled exception", cause)
            call.respond(
                HttpStatusCode.InternalServerError,
                ErrorResponse("Internal server error")
            )
        }
    }
}

@Serializable
data class ErrorResponse(
    val error: String,
    val details: String? = null
)
