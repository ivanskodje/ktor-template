package com.appname.backend.config

import kotlin.time.Duration

data class ApplicationConfig(val database: DatabaseConfig, val server: ServerConfig, val log: LogConfig)

data class DatabaseConfig(
    val url: String,
    val driver: String
)

data class ServerConfig(
    val port: Int,
    val preWait: Duration
)

data class LogConfig(
    val level: String,
    val dir: String,
    val appName: String,
    val maxHistoryDays: Int
)
