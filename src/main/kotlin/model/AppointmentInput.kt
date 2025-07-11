package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class AppointmentInput(
    val clientName: String,
    val clientEmail: String,
    val appointmentTime: String,
    val serviceId: Int
)
