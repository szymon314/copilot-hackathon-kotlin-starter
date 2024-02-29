package com.example.currencyexchange.repository

import com.example.currencyexchange.model.CurrencyExchange
import com.example.currencyexchange.model.CurrencyExchangeId
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class InMemoryCurrencyExchangeRepository(
    private val data: MutableMap<CurrencyExchangeId, CurrencyExchange> = ConcurrentHashMap()
) : CurrencyExchangeRepository {
    override fun save(currencyExchange: CurrencyExchange) {
        data[currencyExchange.id] = currencyExchange
    }

    override fun getCurrencyExchange(id: CurrencyExchangeId): CurrencyExchange? =
        data[id]

    override fun getAllCurrencyExchanges(): List<CurrencyExchange> =
        data.values
            .sortedWith(compareBy({ it.id.currency }, { it.id.date }))
}