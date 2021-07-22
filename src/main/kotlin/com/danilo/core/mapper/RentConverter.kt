package com.danilo.core.mapper

import com.danilo.core.model.Rent
import com.danilo.database.entity.RentEntity
import com.danilo.infrastructure.model.RentEvent

class RentConverter {
    companion object {
        fun rentEventDtoToRent(rentEvent: RentEvent) =
            Rent(rentEvent.id, rentEvent.price, rentEvent.vehicle, rentEvent.brand)

        fun rentToRentEntity(rent: Rent) =
            RentEntity(rent.id, rent.price, rent.vehicle, rent.brand)

        fun rentToRentEventDto(rent: Rent) =
            RentEvent(rent.id, rent.price, rent.vehicle, rent.brand)

        fun rentEntityToRent(rentEntity: RentEntity) =
            Rent(rentEntity.id, rentEntity.price, rentEntity.vehicle, rentEntity.brand)
    }
}