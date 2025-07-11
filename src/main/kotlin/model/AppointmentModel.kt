package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class Appointment(
    val id: Int,
    val clientName: String,
    val clientEmail: String,
    val appointmentTime: String,
    val serviceId: Int
)