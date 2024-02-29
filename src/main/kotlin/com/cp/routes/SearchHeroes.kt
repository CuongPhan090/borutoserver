package com.cp.routes

import com.cp.repository.HeroRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.searchHeroes() {
    val heroRepository: HeroRepository by inject()

    get(path = "boruto/heroes/search") {
        val heroNameQuery = call.request.queryParameters["name"]

        val apiResponse = heroRepository.searchHeroes(heroNameQuery)

        call.respond(
            message = apiResponse,
            status = HttpStatusCode.OK
        )
    }
}
