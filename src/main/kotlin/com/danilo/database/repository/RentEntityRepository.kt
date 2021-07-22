package com.danilo.database.repository

import com.danilo.database.entity.RentEntity
import java.util.UUID
import javax.inject.Singleton

@Singleton
interface RentEntityRepository {
    fun saveCql(rentEntity: RentEntity): RentEntity
    fun deleteCql(id: UUID)
    fun updateCql(rentEntity: RentEntity): RentEntity
}