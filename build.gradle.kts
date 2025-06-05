plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.plugin.serialization)
}

group = "com.appname"
version = "0.1.0"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

repositories {
    mavenCentral()
}

val arrowVersion = "2.1.0"
val suspendAppVersion = "0.5.0"

dependencies {
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.config.yaml)

    implementation("io.ktor:ktor-server-default-headers")
    implementation("io.ktor:ktor-server-compression")

    implementation("io.ktor:ktor-server-status-pages")

    implementation("io.ktor:ktor-server-auth")

    implementation("com.sksamuel.hoplite:hoplite-core:2.8.1")
    implementation("com.sksamuel.hoplite:hoplite-yaml:2.8.1")

    implementation("io.arrow-kt:arrow-core:${arrowVersion}")
    implementation("io.arrow-kt:arrow-functions:${arrowVersion}")
    implementation("io.arrow-kt:arrow-fx-coroutines:${arrowVersion}")
    implementation("io.arrow-kt:arrow-resilience:${arrowVersion}")
    implementation("io.arrow-kt:suspendapp:${suspendAppVersion}")
    implementation("io.arrow-kt:suspendapp-ktor:${suspendAppVersion}")

    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)
    testImplementation("io.kotest:kotest-runner-junit5:5.8.1")
    testImplementation("io.kotest:kotest-assertions-core:5.8.1")
    testImplementation("io.kotest:kotest-framework-engine:5.8.1")
    testImplementation("io.kotest:kotest-property:5.8.1")
    testImplementation("io.mockk:mockk:1.14.2")
}
