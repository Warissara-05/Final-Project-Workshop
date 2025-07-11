package com.example.repository

import com.example.model.Appointment
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object AppointmentRepository {
    private val appointments = mutableListOf<Appointment>()
    private val formatter = DateTimeFormatter.ISO_DATE_TIME

    fun getAll(): List<Appointment> = appointments.toList()

    fun addIfAvailable(new: Appointment): Boolean {
        val newTime = LocalDateTime.parse(new.appointmentTime, formatter)
        val conflict = appointments.any {
            LocalDateTime.parse(it.appointmentTime, formatter) == newTime &&
                    it.serviceId == new.serviceId
        }
        return if (conflict) false else appointments.add(new)
    }
}
