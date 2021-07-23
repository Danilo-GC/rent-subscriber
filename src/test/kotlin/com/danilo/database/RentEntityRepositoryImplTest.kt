package com.danilo.database

import com.danilo.database.entity.RentEntity
import com.danilo.database.repository.impl.RentEntityRepositoryImpl
import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.cql.SimpleStatement
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.mockk
import java.math.BigDecimal
import java.util.UUID

@MicronautTest
class RentEntityRepositoryImplTest : AnnotationSpec() {

    val cqlSession = mockk<CqlSession>(relaxed = true)
    val rentEntityRepositoryImpl = RentEntityRepositoryImpl(cqlSession)

    lateinit var rentEntity: RentEntity

    @BeforeEach
    fun setUp() {
        rentEntity = RentEntity(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"),
            BigDecimal.ONE, "fusca", "volks")
    }

    @Test
    fun `should save with success`() {
        cqlSession.execute(
            SimpleStatement
                .newInstance(
                    "INSERT INTO rent.Rent(id,price,vehicle,brand) VALUES (?,?,?,?)",
                    UUID.randomUUID(),
                    rentEntity.price,
                    rentEntity.vehicle,
                    rentEntity.brand
                )
        )
        val result = rentEntityRepositoryImpl.saveCql(rentEntity)
        result shouldBe rentEntity
    }

    @Test
    fun `should update with success`() {
        cqlSession.execute(
            SimpleStatement.newInstance(
                "UPDATE rent.Rent SET price = ?, vehicle = ?, brand = ? WHERE id = ?",
                rentEntity.price,
                rentEntity.vehicle,
                rentEntity.brand,
                UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6")
            )
        )
        val result = rentEntityRepositoryImpl.updateCql(rentEntity)
        result shouldBe rentEntity
    }

    @Test
    fun `should delete with success`() {
        cqlSession.execute(
            SimpleStatement.newInstance(
                "DELETE from rent.Rent where id = ?",
                rentEntity.id
            )
        )
        val result =
            rentEntityRepositoryImpl.deleteCql(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"))
        result shouldBe Unit

    }

}