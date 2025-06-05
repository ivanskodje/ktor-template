package com.appname.backend.application.port

interface ApiKeyValidator {
    suspend fun validate(token: String): ApiKeyIdentity?
}

data class ApiKeyIdentity(val key: String)
