package com.example.currencyexchange.command

import com.example.currencyexchange.model.Currency
import java.time.LocalDate

data class CurrencyExchangeCommand(
    val fromCurrency: Currency,
    val toCurrency: Currency,
    val amount: Double,
    val date: LocalDate
) {

}
