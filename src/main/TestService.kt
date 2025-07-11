package com.example

import kotlin.test.*
import com.example.model.*
import com.example.repository.*
import kotlinx.coroutines.runBlocking

class TestService {
    @Test
    fun testServiceAddAndRetrieve() {
        val service = Service(1, "Haircut", "Quick cut", 30)

        assertTrue(ServiceRepository.add(service))
        val allServices = ServiceRepository.getAll()
        assertTrue(allServices.contains(service))
    }
}

