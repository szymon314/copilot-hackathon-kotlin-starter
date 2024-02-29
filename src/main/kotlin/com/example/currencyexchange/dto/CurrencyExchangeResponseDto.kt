package com.example.currencyexchange.dto

import java.time.LocalDate

data class CurrencyExchangeResponseDto(
    val currency: String,
    val value: Double,
    val date: LocalDate
)
