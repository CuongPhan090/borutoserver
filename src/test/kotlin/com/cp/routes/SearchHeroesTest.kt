package com.cp.routes

import com.cp.models.ApiResponse
import com.cp.repository.HeroRepositoryImpl
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import org.junit.After
import org.junit.Test
import org.koin.core.context.stopKoin
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SearchHeroesTest {
    private val heroRepository = HeroRepositoryImpl()

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `query an exactly hero name at SearchHeroes endpoint, expect only 1 result return`() = testApplication {
        val heroName = "sasuke"
        client.get(urlString = "/boruto/heroes/search?name=$heroName").apply {
            val expectedApiResponse = ApiResponse(
                success = true,
                message = "ok",
                heroes = heroRepository.findHeroes(heroName)
            )

            val actualApiResponse = Json.decodeFromString<ApiResponse>(bodyAsText())

            assertEquals(
                expected = HttpStatusCode.OK,
                actual = status
            )

            assertEquals(
                expected = expectedApiResponse,
                actual = actualApiResponse
            )

            assertEquals(
                expected = actualApiResponse.heroes.size,
                actual = 1
            )
        }
    }

    @Test
    fun `query a generic hero name at SearchHeroes endpoint, expect more than one result return`() = testApplication {
        val heroName = "sa"
        client.get(urlString = "/boruto/heroes/search?name=$heroName").apply {
            val expectedApiResponse = ApiResponse(
                success = true,
                message = "ok",
                heroes = heroRepository.findHeroes(heroName)
            )

            val actualApiResponse = Json.decodeFromString<ApiResponse>(bodyAsText())

            assertEquals(
                expected = HttpStatusCode.OK,
                actual = status
            )

            assertEquals(
                expected = expectedApiResponse,
                actual = actualApiResponse
            )

            assertTrue(actual = actualApiResponse.heroes.size > 1)
        }
    }

    @Test
    fun `query a non existing hero name at SearchHeroes endpoint, expect no result return`() = testApplication {
        val heroName = "xyz"
        client.get(urlString = "/boruto/heroes/search?name=$heroName").apply {
            val expectedApiResponse = ApiResponse(
                success = true,
                message = "ok",
                heroes = heroRepository.findHeroes(heroName)
            )

            val actualApiResponse = Json.decodeFromString<ApiResponse>(bodyAsText())

            assertEquals(
                expected = HttpStatusCode.OK,
                actual = status
            )

            assertEquals(
                expected = expectedApiResponse,
                actual = actualApiResponse
            )

            assertEquals(
                expected = actualApiResponse.heroes.size,
                actual = 0
            )
        }
    }

    @Test
    fun `access SearchHeroes endpoint without query parameter, expect no result return`() = testApplication {
        val heroName = ""
        client.get(urlString = "/boruto/heroes/search").apply {
            val expectedApiResponse = ApiResponse(
                success = true,
                message = "ok",
                heroes = heroRepository.findHeroes(heroName)
            )

            val actualApiResponse = Json.decodeFromString<ApiResponse>(bodyAsText())

            assertEquals(
                expected = HttpStatusCode.OK,
                actual = status
            )

            assertEquals(
                expected = expectedApiResponse,
                actual = actualApiResponse
            )

            assertEquals(
                expected = actualApiResponse.heroes.size,
                actual = 0
            )
        }
    }
}
