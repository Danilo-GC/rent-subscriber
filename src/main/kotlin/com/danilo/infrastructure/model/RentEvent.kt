package com.danilo.infrastructure.model

import io.micronaut.core.annotation.Introspected
import java.math.BigDecimal
import java.util.UUID

@Introspected
data class RentEvent(
    val id: UUID? = null,
    val price: BigDecimal = BigDecimal.ZERO,
    val vehicle: String? = "",
    val brand: String? = "",
)