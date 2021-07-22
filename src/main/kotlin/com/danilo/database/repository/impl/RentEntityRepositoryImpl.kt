package com.danilo.database.repository.impl

import com.danilo.database.entity.RentEntity
import com.danilo.database.repository.RentEntityRepository
import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.cql.SimpleStatement
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.UUID
import javax.inject.Singleton

@Singleton
class RentEntityRepositoryImpl(private val cqlSession: CqlSession) : RentEntityRepository {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun saveCql(rentEntity: RentEntity): RentEntity {
        cqlSession.execute(
            SimpleStatement.newInstance(
                "INSERT INTO rent.Rent(id,price,vehicle,brand) VALUES (?,?,?,?)",
                UUID.randomUUID(),
                rentEntity.price,
                rentEntity.vehicle,
                rentEntity.brand
            )
        )
        log.info("rent added")
        return rentEntity
    }

    override fun deleteCql(id: UUID) {
        cqlSession.execute(
            SimpleStatement.newInstance(
                "DELETE from rent.Rent where id = ?",
                id
            )
        )
        log.info("rent deleted")

    }

    override fun updateCql(rentEntity: RentEntity): RentEntity {
        cqlSession.execute(
            SimpleStatement.newInstance(
                "UPDATE rent.Rent SET price = ?, vehicle = ?, brand = ? WHERE id = ?",
                rentEntity.price,
                rentEntity.vehicle,
                rentEntity.brand,
                rentEntity.id
            )
        )
        log.info("rent updated")
        return rentEntity
    }
}