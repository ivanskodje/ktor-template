package com.appname.backend.infrastructure.auth

import com.appname.backend.application.port.ApiKeyIdentity
import com.appname.backend.application.port.ApiKeyValidator

class InMemoryApiKeyValidator(
    private val allowed: Set<String> = setOf("demo-key")
) : ApiKeyValidator {
    override suspend fun validate(token: String) =
        if (token in allowed) ApiKeyIdentity(token) else null
}
