package com.example.currencyexchange.service

import com.example.currencyexchange.dto.CurrencyDto
import com.example.currencyexchange.model.Currency
import com.example.currencyexchange.model.CurrencyExchange
import com.example.currencyexchange.model.CurrencyExchangeId
import com.example.currencyexchange.model.ExchangeRate
import com.example.currencyexchange.repository.CurrencyExchangeRepository
import org.springframework.stereotype.Service

@Service
class CurrencyExchangeStorageService(
    private val currencyExchangeRepository: CurrencyExchangeRepository
) {
    fun saveAll(currencies: List<CurrencyDto>) {
        currencies.forEach {
            currencyExchangeRepository.save(it.toCurrencyExchange())
        }
    }

    private fun CurrencyDto.toCurrencyExchange(): CurrencyExchange {
        return CurrencyExchange(
            id = CurrencyExchangeId(
                currency = Currency(currency),
                date = date,
            ),
            exchangeToBaseCurrency = ExchangeRate(pricePln.toBigDecimal())
        )
    }

    fun getAllCurrencies(): List<CurrencyDto> {
        currencyExchangeRepository.getAllCurrencyExchanges().let { currencyExchanges ->
            return currencyExchanges.map {
                CurrencyDto(
                    currency = it.id.currency.value,
                    pricePln = it.exchangeToBaseCurrency.value.toString().trimEnd('0').trimEnd('.'),
                    date = it.id.date
                )
            }
        }
    }
}