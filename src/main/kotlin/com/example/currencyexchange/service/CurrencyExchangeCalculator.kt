package com.example.currencyexchange.service

import com.example.currencyexchange.command.CurrencyExchangeCalculationResult
import com.example.currencyexchange.command.CurrencyExchangeCommand
import com.example.currencyexchange.exceptions.CurrencyExchangeNotFoundException
import com.example.currencyexchange.model.CurrencyExchangeId
import com.example.currencyexchange.repository.CurrencyExchangeRepository
import org.springframework.stereotype.Component

@Component
class CurrencyExchangeCalculator(
    private val currencyExchangeRepository: CurrencyExchangeRepository
) {
    fun calculateExchange(currencyExchangeCommand: CurrencyExchangeCommand): CurrencyExchangeCalculationResult {
        val currencyExchange = currencyExchangeRepository.getCurrencyExchange(
            CurrencyExchangeId(
                currencyExchangeCommand.fromCurrency,
                currencyExchangeCommand.date
            )
        ) ?: throw CurrencyExchangeNotFoundException(currencyExchangeCommand.fromCurrency, currencyExchangeCommand.date)

        if (currencyExchangeCommand.toCurrency.isBaseCurrency()) {
            return CurrencyExchangeCalculationResult(
                currencyExchangeCommand.amount.toBigDecimal() * currencyExchange.exchangeToBaseCurrency.value,
                currencyExchangeCommand.toCurrency,
                currencyExchangeCommand.date
            )

        } else {
            val toCurrencyExchange = currencyExchangeRepository.getCurrencyExchange(
                CurrencyExchangeId(
                    currencyExchangeCommand.toCurrency,
                    currencyExchangeCommand.date
                )
            ) ?: throw CurrencyExchangeNotFoundException(
                currencyExchangeCommand.fromCurrency,
                currencyExchangeCommand.date
            )

            return CurrencyExchangeCalculationResult(
                currencyExchangeCommand.amount.toBigDecimal() * currencyExchange.exchangeToBaseCurrency.value / toCurrencyExchange.exchangeToBaseCurrency.value,
                currencyExchangeCommand.toCurrency,
                currencyExchangeCommand.date
            )
        }
    }
}
