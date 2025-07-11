package com.example.repository

import com.example.model.Appointment
import com.example.model.AppointmentInput
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object AppointmentRepository {
    val appointments = mutableListOf<Appointment>()
    var nextId = 1
    val formatter = DateTimeFormatter.ISO_DATE_TIME

    /** คืน true ถ้าจองได้, false ถ้าเวลา/บริการซ้ำ */
    fun addIfAvailable(input: AppointmentInput): Boolean {
        val newTime = LocalDateTime.parse(input.appointmentTime, formatter)

        val conflict = appointments.any {
            LocalDateTime.parse(it.appointmentTime, formatter) == newTime &&
                    it.serviceId == input.serviceId
        }

        if (conflict) return false

        val newAppointment = Appointment(
            id = nextId++,
            clientName = input.clientName,
            clientEmail = input.clientEmail,
            appointmentTime = input.appointmentTime,
            serviceId = input.serviceId
        )
        appointments.add(newAppointment)
        return true
    }

    fun getAll(): List<Appointment> = appointments.toList()
}
