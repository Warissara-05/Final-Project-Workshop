package com.example.repository

import com.example.model.Service

object ServiceRepository {
    private val services = mutableListOf<Service>()

    fun add(service: Service): Boolean {
        if (services.any { it.name.equals(service.name, ignoreCase = true) })
            return false
        return services.add(service)
    }

    fun getAll(): List<Service> = services.toList()
}
