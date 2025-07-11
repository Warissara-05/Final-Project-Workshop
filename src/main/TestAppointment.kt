package com.example

import com.example.model.*
import com.example.repository.AppointmentRepository
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.*

class AppointmentRouteTest {

    @Test
    fun `GET and DELETE appointment by id`() = testApplication {
        val input = AppointmentInput(
            clientName = "Alice",
            clientEmail = "alice@gmail.com",
            appointmentTime = "2025-07-05T14:00:00",
            serviceId = 1
        )

        val added = AppointmentRepository.addIfAvailable(input)
        assertTrue(added)

        val id = AppointmentRepository.getAll().last().id

        val getResponse = client.get("/appointment/$id")
        assertEquals(HttpStatusCode.OK, getResponse.status)
        val getBody = getResponse.bodyAsText()
        assertTrue(getBody.contains("Alice"))

        val delResponse = client.delete("/appointment/$id")
        assertEquals(HttpStatusCode.OK, delResponse.status)
        val delBody = delResponse.bodyAsText()
        assertTrue(delBody.contains("Deleted"))

        val getAfterDel = client.get("/appointment/$id")
        assertEquals(HttpStatusCode.NotFound, getAfterDel.status)
    }
}
