package com.danilo.infrastructure

import com.danilo.core.ports.RentServicePort
import com.danilo.infrastructure.model.Events
import com.danilo.infrastructure.model.EventsInformation
import com.danilo.infrastructure.model.RentEvent
import com.danilo.infrastructure.subscribe.RentServer
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal
import java.util.UUID

@MicronautTest
class RentServerTest : AnnotationSpec() {

    val rentServicePort = mockk<RentServicePort>()
    val rentServer = RentServer(rentServicePort)

    lateinit var rentEvent: RentEvent
    lateinit var eventsInformationSave: EventsInformation
    lateinit var eventsInformationDelete: EventsInformation
    lateinit var eventsInformationUpdate: EventsInformation

    @BeforeEach
    fun setUp() {
        rentEvent = RentEvent(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"),
            BigDecimal.ONE, "fusca", "volks")
        eventsInformationSave = EventsInformation(Events.SAVE_RENT,
            RentEvent(null, BigDecimal.ZERO, "", ""))
        eventsInformationDelete = EventsInformation(Events.DELETE_RENT,
            RentEvent(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"), BigDecimal.ZERO, "", ""))
        eventsInformationUpdate = EventsInformation(Events.UPDATE_RENT,
            RentEvent(UUID.fromString("d5e493a3-61a0-4bbb-9128-a4c893d929b6"), BigDecimal.ZERO, "", ""))


    }

    @Test
    fun `should save with success`() {
        every {
            rentServicePort.insert(any())
        } answers { eventsInformationSave.rent }

        val result = rentServer.receive(eventsInformationSave)
        result shouldBe Unit
    }

    @Test
    fun `should delete with success`() {
        every {
            rentServicePort.delete(any())
        } answers { Unit }
        val result = rentServer.receive(eventsInformationDelete)
        result shouldBe Unit
    }

    @Test
    fun `should update with success`() {
        every {
            rentServicePort.update(any())
        } answers { eventsInformationUpdate.rent }

        val result = rentServer.receive(eventsInformationUpdate)
        result shouldBe Unit
    }
}