package com.danilo.infrastructure.model

import java.math.BigDecimal

data class EventsInformation(
    val events: Events = Events.SAVE_RENT,
    val rent: RentEvent = RentEvent(null, BigDecimal.ZERO, "", ""),
)