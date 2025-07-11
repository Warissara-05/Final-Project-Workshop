package com.example

import kotlin.test.*
import com.example.model.*
import com.example.repository.*

class TestService {

    @Test
    fun `add and retrieve service`() {
        val service = Service(
            id = 1,
            name = "Haircut",
            description = "Simple cut",
            defaultDurationInMinutes = 30
        )

        assertTrue(ServiceRepository.add(service))
        assertTrue(ServiceRepository.getAll().contains(service))
    }
}
