package com.appname.backend

import com.appname.backend.application.port.ApiKeyIdentity
import com.appname.backend.application.port.ApiKeyValidator
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*

class ApplicationTest : FunSpec({

    test("GET /health should return 200 OK with text OK") {
        testApplication {
            application(applicationModule())

            val response = client.get("/health")
            response.status shouldBe HttpStatusCode.Companion.OK
            response.bodyAsText() shouldBe "OK"
        }
    }

    test("GET /info should return 403 UNAUTHORIZED with invalid api key") {
        val mockValidator = object : ApiKeyValidator {
            override suspend fun validate(token: String) = null  // always invalid
        }

        testApplication {
            application(applicationModule(mockValidator))


            client.get("/info") {
                header(HttpHeaders.Authorization, "Bearer invalid-key")
            }.status shouldBe HttpStatusCode.Unauthorized
        }
    }

    test("GET /info should return 403 UNAUTHORIZED with missing api key") {
        val mockValidator = object : ApiKeyValidator {
            override suspend fun validate(token: String) = null  // always invalid
        }

        testApplication {
            application(applicationModule(mockValidator))


            client.get("/info")
                .status shouldBe HttpStatusCode.Unauthorized
        }
    }


    test("GET /info should return 200 OK with valid api key") {
        val mockValidator = object : ApiKeyValidator {
            override suspend fun validate(token: String) = ApiKeyIdentity("good-key")
        }

        testApplication {
            application(applicationModule(mockValidator))

            val response = client.get("/info") {
                header(HttpHeaders.Authorization, "Bearer good-key")
            }
            response.status shouldBe HttpStatusCode.OK
        }
    }
})
