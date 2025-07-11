package com.example.repository

import com.example.model.Service

object ServiceRepository {
    private val services = mutableListOf<Service>()

    fun add(service: Service): Boolean = services.add(service)
    fun getAll(): List<Service> = services.toList()
}
