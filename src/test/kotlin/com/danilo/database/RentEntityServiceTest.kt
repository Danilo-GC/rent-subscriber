package com.danilo.database

import com.danilo.core.model.Rent
import com.danilo.database.entity.RentEntity
import com.danilo.database.repository.RentEntityRepository
import com.danilo.database.service.RentEntityService
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal
import java.util.UUID

@MicronautTest
class RentEntityServiceTest : AnnotationSpec() {

    val rentEntityRepository = mockk<RentEntityRepository>()
    val rentService = RentEntityService(rentEntityRepository)

    lateinit var rent: Rent
    lateinit var rentEntity: RentEntity

    @BeforeEach
    fun setUp() {
        rent = Rent(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"),
            BigDecimal.ONE, "fusca", "volks")
        rentEntity = RentEntity(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"),
            BigDecimal.ONE, "fusca", "volks")
    }

    @Test
    fun `should save with success`() {
        every {
            rentEntityRepository.saveCql(any())
        } answers { rentEntity }

        val result = rentService.save(rentEntity)
        result shouldBe rent
    }

    @Test
    fun `should update with success`() {
        every {
            rentEntityRepository.updateCql(any())
        } answers { rentEntity }

        val result = rentService.update(rentEntity)
        result shouldBe rent
    }

    @Test
    fun `should delete with success`() {
        every {
            rentEntityRepository.deleteCql(rentEntity.id!!)
        } answers {}

        val result =
            rentService.delete(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"))
        result shouldBe Unit
    }
}