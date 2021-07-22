package com.danilo.core.ports

import com.danilo.core.model.Rent
import com.danilo.database.entity.RentEntity
import java.util.UUID
import javax.inject.Singleton

@Singleton
interface RentEntityServicePort {
    fun save(rentEntity: RentEntity): Rent
    fun delete(id: UUID)
    fun update(rentEntity: RentEntity): Rent
}