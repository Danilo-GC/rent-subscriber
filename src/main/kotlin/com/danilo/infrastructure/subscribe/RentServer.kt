package com.danilo.infrastructure.subscribe

import com.danilo.core.mapper.RentConverter
import com.danilo.core.ports.RentServicePort
import com.danilo.infrastructure.model.EventsInformation
import io.micronaut.nats.annotation.NatsListener
import io.micronaut.nats.annotation.Subject
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@NatsListener
class RentServer(private val service: RentServicePort) {

    val log: Logger = LoggerFactory.getLogger(RentServer::class.java)

    @Subject("store.rent")
    fun receive(eventsInformation: EventsInformation) {
        log.info("mensagem recebida{}" )
        when (eventsInformation.events.event) {
            "SAVE_RENT" -> service.insert(
                RentConverter.rentEventDtoToRent(eventsInformation.rent)
            )
            "DELETE_RENT" -> service.delete(
                eventsInformation.rent.id!!
            )
            "UPDATE_RENT" -> service.update(
                RentConverter.rentEventDtoToRent(eventsInformation.rent)
            )
        }
    }
}