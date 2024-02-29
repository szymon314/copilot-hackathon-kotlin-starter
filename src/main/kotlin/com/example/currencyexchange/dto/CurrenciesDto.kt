package com.example.currencyexchange.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class CurrencyDto(
    val currency: String,
    @JsonProperty("price_pln")
    val pricePln: String,
    val date: LocalDate
)

data class CurrenciesDto(
    val currencies: List<CurrencyDto>
)
