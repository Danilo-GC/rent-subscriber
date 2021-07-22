package com.danilo.database.service

import com.danilo.core.mapper.RentConverter
import com.danilo.core.model.Rent
import com.danilo.core.ports.RentEntityServicePort
import com.danilo.database.entity.RentEntity
import com.danilo.database.repository.RentEntityRepository
import java.util.UUID
import javax.inject.Singleton

@Singleton
class RentService(private val rentEntityRepository: RentEntityRepository) : RentEntityServicePort {
    override fun save(rentEntity: RentEntity): Rent =
        RentConverter.rentEntityToRent(rentEntityRepository.saveCql(rentEntity))

    override fun delete(id: UUID) {
        rentEntityRepository.deleteCql(id)
    }

    override fun update(rentEntity: RentEntity): Rent =
        RentConverter.rentEntityToRent(rentEntityRepository.updateCql(rentEntity))
}

