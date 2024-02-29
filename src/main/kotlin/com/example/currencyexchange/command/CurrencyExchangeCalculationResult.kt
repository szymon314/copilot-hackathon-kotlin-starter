package com.example.currencyexchange.command

import com.example.currencyexchange.model.Currency
import java.math.BigDecimal
import java.time.LocalDate

data class CurrencyExchangeCalculationResult(
    val value: BigDecimal,
    val currency: Currency,
    val date: LocalDate
)
