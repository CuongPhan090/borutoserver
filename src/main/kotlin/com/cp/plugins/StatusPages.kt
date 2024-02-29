package com.cp.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPages() {
    install(StatusPages) {
        status(HttpStatusCode.NotFound) { _ ->
            call.respond(
                message = "Page Not Found",
                status = HttpStatusCode.NotFound
            )
        }
    }
}
