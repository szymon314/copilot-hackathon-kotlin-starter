package com.example.currencyexchange.model

import java.math.BigDecimal
import java.time.LocalDate

class CurrencyExchange(
    val id: CurrencyExchangeId,
    val exchangeToBaseCurrency: ExchangeRate
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CurrencyExchange) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}

data class CurrencyExchangeId(
    val currency: Currency,
    val date: LocalDate,
)


@JvmInline
value class ExchangeRate(val value: BigDecimal)

@JvmInline
value class Currency(val value: String) : Comparable<Currency> {
    override fun compareTo(other: Currency): Int {
        return value.compareTo(other.value)
    }

    fun isBaseCurrency() = value == "PLN"
}
