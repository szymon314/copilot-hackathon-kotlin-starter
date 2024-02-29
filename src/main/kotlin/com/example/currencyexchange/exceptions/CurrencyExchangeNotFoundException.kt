package com.example.currencyexchange.exceptions

import com.example.currencyexchange.model.Currency
import java.time.LocalDate

class CurrencyExchangeNotFoundException(fromCurrency: Currency, date: LocalDate) : RuntimeException() {

}
