package com.appname.backend.plugin

import com.appname.backend.application.port.ApiKeyValidator
import io.ktor.server.application.*
import io.ktor.server.auth.*

const val API_KEY_AUTH = "api-key"
const val REALM = "AppNameApi"

fun Application.configureBearerApiKeyAuth(validator: ApiKeyValidator) {
    install(Authentication) {
        bearer(API_KEY_AUTH) {
            realm = REALM
            authenticate { cred -> validator.validate(cred.token) }
        }
    }
}
