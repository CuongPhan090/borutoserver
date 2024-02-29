package com.cp.routes

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.After
import org.koin.core.context.stopKoin
import kotlin.test.Test
import kotlin.test.assertEquals

class RootTest {

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `query root endpoint, assert correct response`() = testApplication {
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Welcome to Boruto API!", bodyAsText())
        }
    }
}
