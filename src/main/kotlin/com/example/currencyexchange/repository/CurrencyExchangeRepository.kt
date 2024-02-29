package com.example.currencyexchange.repository

import com.example.currencyexchange.model.CurrencyExchange
import com.example.currencyexchange.model.CurrencyExchangeId

interface CurrencyExchangeRepository {
    fun save(currencyExchange: CurrencyExchange)

    fun getCurrencyExchange(id: CurrencyExchangeId): CurrencyExchange?
    fun getAllCurrencyExchanges(): List<CurrencyExchange>
}
