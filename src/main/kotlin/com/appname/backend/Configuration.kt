package com.appname.backend

import arrow.core.memoize
import com.sksamuel.hoplite.ConfigLoader
import com.sksamuel.hoplite.ExperimentalHoplite
import com.sksamuel.hoplite.addResourceSource
import com.appname.backend.config.ApplicationConfig

/**
 * Must be loaded before any logger to apply log properties
 */
@OptIn(ExperimentalHoplite::class)
val applicationConfig: () -> ApplicationConfig = {
    ConfigLoader.builder()
        .addResourceSource("/application.yml", optional = false)
        .withExplicitSealedTypes()
        .build()
        .loadConfigOrThrow<ApplicationConfig>()
        .also(::setLogbackSystemProperties)
}.memoize()

private fun setLogbackSystemProperties(config: ApplicationConfig) {
    config.log.run {
        System.setProperty("LOG_LEVEL", level)
        System.setProperty("LOG_DIR", dir)
        System.setProperty("APP_NAME", appName)
        System.setProperty("MAX_HISTORY_DAYS", maxHistoryDays.toString())
    }
}
