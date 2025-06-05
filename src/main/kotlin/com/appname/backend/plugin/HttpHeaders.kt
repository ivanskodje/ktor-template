package com.appname.backend.plugin

import io.ktor.server.application.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.defaultheaders.*

/**
 * DefaultHeaders improves caching and debugging.
 * Compression reduces payload size for responses.
 */
fun Application.configureHttpHeaders() {
    install(DefaultHeaders)
    install(Compression) {
        gzip()
    }
}
