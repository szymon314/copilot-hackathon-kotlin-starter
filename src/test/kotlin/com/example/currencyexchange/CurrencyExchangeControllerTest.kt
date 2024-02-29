package com.example.currencyexchange

import com.example.currencyexchange.dto.CurrencyDto
import com.example.currencyexchange.dto.CurrencyExchangeRequestDto
import com.example.currencyexchange.service.CurrencyExchangeStorageService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyExchangeControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var currencyExchangeStorageService: CurrencyExchangeStorageService

    @Test
    fun `calculate from eur to pln`() {
        val currencies = listOf(
            CurrencyDto("EUR", "4.31", LocalDate.parse("2022-01-01")),
        )
        currencyExchangeStorageService.saveAll(currencies)

        val requestDto = CurrencyExchangeRequestDto("EUR", "PLN", 123.33, LocalDate.parse("2022-01-01"))
        val jsonRequest = objectMapper.writeValueAsString(requestDto)

        mockMvc.perform(
            post("/currencyExchange")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.currency").value("PLN"))
            .andExpect(jsonPath("$.value").value("531.5523"))
    }

    @Test
    fun `calculate from eur to usd`() {
        val currencies = listOf(
            CurrencyDto("EUR", "4", LocalDate.parse("2022-01-01")),
            CurrencyDto("USD", "2", LocalDate.parse("2022-01-01"))
        )
        currencyExchangeStorageService.saveAll(currencies)

        val requestDto = CurrencyExchangeRequestDto("EUR", "USD", 400.0, LocalDate.parse("2022-01-01"))
        val jsonRequest = objectMapper.writeValueAsString(requestDto)

        mockMvc.perform(
            post("/currencyExchange")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.currency").value("USD"))
            .andExpect(jsonPath("$.value").value("800.0"))
    }
}