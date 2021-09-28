package com.costa.matheus.data.mapper

import com.costa.matheus.data.model.DayOfferModel
import com.costa.matheus.domain.entities.DayOfferEntity

class DayOfferMapper {

    fun map(model: DayOfferModel): DayOfferEntity {
        return DayOfferEntity(model.image)
    }
}