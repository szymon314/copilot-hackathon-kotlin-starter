package com.example.currencyexchange.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class CurrencyExchangeRequestDto(
    @JsonProperty("from_currency")
    val fromCurrency: String,
    @JsonProperty("to_currency")
    val toCurrency: String,
    val amount: Double,
    val date: LocalDate
)