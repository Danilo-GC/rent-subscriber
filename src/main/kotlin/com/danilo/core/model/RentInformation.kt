package com.danilo.core.model

import com.danilo.infrastructure.model.Events
import com.danilo.infrastructure.model.RentEvent
import java.math.BigDecimal

data class RentInformation(
    val deleteEvent: Events = Events.DELETE_RENT,
    val saveEvent: Events = Events.SAVE_RENT,
    val updateEvent: Events = Events.UPDATE_RENT,
    val rent: RentEvent = RentEvent(null, BigDecimal.ZERO, "", ""),
)