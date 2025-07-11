package com.example

import kotlin.test.*
import com.example.model.*
import com.example.repository.*

class TestAppointment {

    @Test
    fun `no double booking for same service & time`() {
        val in1 = AppointmentInput(
            clientName = "Alice",
            clientEmail = "alice@mail.com",
            appointmentTime = "2025-07-05T14:00:00",
            serviceId = 1
        )

        assertTrue(AppointmentRepository.addIfAvailable(in1))
        assertFalse(AppointmentRepository.addIfAvailable(in2))
    }
}
