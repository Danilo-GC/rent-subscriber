package com.danilo.core

import com.danilo.core.model.Rent
import com.danilo.core.ports.RentEntityServicePort
import com.danilo.core.service.RentService
import com.danilo.database.entity.RentEntity
import com.danilo.infrastructure.model.RentEvent
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal
import java.util.UUID

@MicronautTest
class RentServiceTest : AnnotationSpec() {

    val rentEntityServicePort = mockk<RentEntityServicePort>()
    val rentService = RentService(rentEntityServicePort)

    lateinit var rent: Rent
    lateinit var rentEvent: RentEvent
    lateinit var rentEntity: RentEntity

    @BeforeEach
    fun setUp() {
        rent = Rent(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"),
            BigDecimal.ONE, "fusca", "volks")
        rentEvent = RentEvent(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"),
            BigDecimal.ONE, "fusca", "volks")
        rentEntity = RentEntity(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"),
            BigDecimal.ONE, "fusca", "volks")
    }

    @Test
    fun `should insert with success`() {
        every {
            rentEntityServicePort.save(any())
        } answers { rent }

        val result = rentService.insert(rent)
        result shouldBe rentEvent
    }

    @Test
    fun `should update with success`() {
        every {
            rentEntityServicePort.update(any())
        } answers { rent }

        val result = rentService.update(rent)
        result shouldBe rentEvent
    }

    @Test
    fun `should delete with success`() {
        every {
            rentEntityServicePort.delete(rentEvent.id!!)
        } answers {}

        val result =
            rentService.delete(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"))
        result shouldBe Unit
    }
}
