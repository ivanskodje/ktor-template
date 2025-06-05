package com.appname.backend.infrastructure.web.routes

import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.healthCheckRoute() {
    get("/health") {
        call.respondText("OK")
    }
}
