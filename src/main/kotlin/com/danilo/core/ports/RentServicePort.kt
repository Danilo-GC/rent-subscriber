package com.danilo.core.ports

import com.danilo.core.model.Rent
import com.danilo.infrastructure.model.RentEvent
import java.util.UUID
import javax.inject.Singleton

@Singleton
interface RentServicePort {
    fun insert(rent: Rent): RentEvent
    fun delete(id: UUID)
    fun update(rent: Rent): RentEvent
}