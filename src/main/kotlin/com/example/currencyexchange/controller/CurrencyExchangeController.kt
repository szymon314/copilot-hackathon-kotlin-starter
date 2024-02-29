package com.example.currencyexchange.controller

import com.example.currencyexchange.command.CurrencyExchangeCommand
import com.example.currencyexchange.dto.CurrencyExchangeRequestDto
import com.example.currencyexchange.dto.CurrencyExchangeResponseDto
import com.example.currencyexchange.model.Currency
import com.example.currencyexchange.service.CurrencyExchangeCalculator
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CurrencyExchangeController(
    private val currencyExchangeCalculator: CurrencyExchangeCalculator
) {

    @PostMapping("/currencyExchange")
    fun exchange(@RequestBody currencyExchangeRequestDto: CurrencyExchangeRequestDto): CurrencyExchangeResponseDto {
        return currencyExchangeCalculator.calculateExchange(
            CurrencyExchangeCommand(
                Currency(currencyExchangeRequestDto.fromCurrency),
                Currency(currencyExchangeRequestDto.toCurrency),
                currencyExchangeRequestDto.amount,
                currencyExchangeRequestDto.date
            )
        ).let {
            CurrencyExchangeResponseDto(
                it.currency.value,
                it.value.toDouble(),
                it.date
            )
        }
    }

}