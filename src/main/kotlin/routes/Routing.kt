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
                call.respond(HttpStatusCode.Created,"Appointment completed")
            else
                call.respond(HttpStatusCode.Conflict, "Time already booked")
        }
    }

    get("{id}") {
        val id = call.parameters["id"]?.toIntOrNull()
        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, "Invalid ID")
            return@get
        }

        val appt = AppointmentRepository.getById(id)
        if (appt == null)
            call.respond(HttpStatusCode.NotFound, "Appointment not found")
        else
            call.respond(appt)
    }

    delete("{id}") {
        val id = call.parameters["id"]?.toIntOrNull()
        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, "Invalid ID")
            return@delete
        }

        val removed = AppointmentRepository.deleteById(id)
        if (removed)
            call.respond(HttpStatusCode.OK, "Deleted appointment")
        else
            call.respond(HttpStatusCode.NotFound, "Appointment not found")
    }
}
