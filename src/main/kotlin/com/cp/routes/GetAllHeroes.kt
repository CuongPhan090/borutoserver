package com.cp.routes

import com.cp.models.ApiResponse
import com.cp.repository.HeroRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.getAllHeroes() {
    val heroRepository: HeroRepository by inject()

    get(path = "/boruto/heroes") {
        try {
            val pageNumber = call.request.queryParameters["page"]?.toInt() ?: 1
            require(pageNumber in 1..5)

            call.respond(
                message = heroRepository.getAllHeroes(pageNumber),
                status = HttpStatusCode.OK
            )
        } catch (e: NumberFormatException) {
            call.respond(
                message = ApiResponse(success = false, message = "Only Numbers Allowed"),
                status = HttpStatusCode.BadRequest
            )
        } catch (e: IllegalArgumentException) {
            call.respond(
                message = ApiResponse(success = false, message = "Page number must be from 1 to 5"),
                status = HttpStatusCode.BadRequest
            )
        }
    }
}
