package com.danilo.core.service

import com.danilo.core.mapper.RentConverter
import com.danilo.core.model.Rent
import com.danilo.core.ports.RentEntityServicePort
import com.danilo.core.ports.RentServicePort
import com.danilo.infrastructure.model.RentEvent
import java.util.UUID
import javax.inject.Singleton

@Singleton
class RentService(private val service: RentEntityServicePort) : RentServicePort {
    override fun insert(rent: Rent): RentEvent =
        RentConverter.rentToRentEventDto(
            service.save(RentConverter.rentToRentEntity(rent))
        )

    override fun delete(id: UUID) {
        service.delete(id)
    }

    override fun update(rent: Rent): RentEvent =
        RentConverter.rentToRentEventDto(
            service.update(RentConverter.rentToRentEntity(rent))
        )
}
