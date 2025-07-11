package com.example.routes

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.http.*
import com.example.model.*
import com.example.repository.*

fun Application.configureRouting() {
    routing {
        route("/service") {
            get {
                call.respond(ServiceRepository.getAll())
            }
            post {
                val service = call.receive<Service>()
                val success = ServiceRepository.add(service)
                if (success)
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
                val appointment = call.receive<Appointment>()
                val success = AppointmentRepository.addIfAvailable(appointment)
                if (success)
                    call.respond(HttpStatusCode.Created, "Appointment created")
                else
                    call.respond(HttpStatusCode.Conflict, "Time already booked")
            }
        }
     /*   post {
            try {
                val appointment = call.receive<Appointment>()
                val success = AppointmentRepository.addIfAvailable(appointment)
                if (success)
                    call.respond(HttpStatusCode.Created, appointment)
                else
                    call.respond(HttpStatusCode.Conflict, "Time already booked")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Invalid request: ${e.message}")
            }
        }*/
    }
}
