package com.appname.backend.plugin

import com.appname.backend.infrastructure.web.routes.configureInfoRoute
import com.appname.backend.infrastructure.web.routes.healthCheckRoute
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        healthCheckRoute()

        authenticate(API_KEY_AUTH) {
            configureInfoRoute()
        }
    }
}
