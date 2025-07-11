package com.example

import kotlin.test.*
import com.example.model.*
import com.example.repository.*
import kotlinx.coroutines.runBlocking

class TestAppointment {
    @Test
    fun testNoDoubleBooking() = runBlocking {
        val app1 = Appointment(1, "Alice", "a@mail.com", "2025-07-05T14:00:00", 1)
        val app2 = Appointment(2, "Bob", "b@mail.com", "2025-07-05T14:00:00", 1)

        assertTrue(AppointmentRepository.addIfAvailable(app1))
        assertFalse(AppointmentRepository.addIfAvailable(app2))
    }
}
