package com.example.routes

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.http.*
import com.example.model.*
import com.example.repository.*
import com.example.repository.AppointmentRepository

fun Application.configureRouting() = routing {

    route("/service") {
        get {
            call.respond(ServiceRepository.getAll())
        }
        post {
            val service = call.receive<Service>()
            if (ServiceRepository.add(service))
                call.respond(HttpStatusCode.Created, "Service created")
            else
                call.respond(HttpStatusCode.InternalServerError, "Failed to add service")
        }
    }

    route("/appointment") {
        get {
            call.respond(AppointmentRepository.getAll())
        }
        post {
            val input = call.receive<AppointmentInput>()
            if (AppointmentRepository.addIfAvailable(input))
                call.respond(HttpStatusCode.Created)
            else
                call.respond(HttpStatusCode.Conflict, "Time already booked")
        }
    }
}
