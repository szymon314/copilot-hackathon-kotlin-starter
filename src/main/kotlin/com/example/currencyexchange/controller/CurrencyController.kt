package com.example.currencyexchange.controller

import com.example.currencyexchange.dto.CurrenciesDto
import com.example.currencyexchange.service.CurrencyExchangeStorageService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class CurrencyController(
    private val currencyExchangeStorageService: CurrencyExchangeStorageService
) {

    @ResponseStatus(CREATED)
    @PostMapping("/currency")
    fun saveCurrency(@RequestBody currenciesDto: CurrenciesDto) {
        currencyExchangeStorageService.saveAll(currenciesDto.currencies)
    }

    @GetMapping("/currency")
    fun getAllCurrencies(): CurrenciesDto {
        return CurrenciesDto(currencyExchangeStorageService.getAllCurrencies())
    }
}