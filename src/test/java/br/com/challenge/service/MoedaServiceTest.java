package br.com.challenge.service;

import br.com.challenge.model.ExchangeRateRequestDTO;
import br.com.challenge.model.ExchangeRateResponseDTO;
import br.com.challenge.model.MoedaEntity;
import br.com.challenge.model.MoedaResponseDTO;
import br.com.challenge.repository.MoedaRepository;
import br.com.challenge.rest.ExchangeRestClient;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@QuarkusTest
class MoedaServiceTest {
    @InjectMock
    @RestClient
    ExchangeRestClient exchangeRestClient;

    @InjectMock
    MoedaRepository moedaRepository;

    @Inject
    MoedaService exchangeService;

    @Test
    void testConverterMoeda() {
        ExchangeRateRequestDTO requestDTO = new ExchangeRateRequestDTO();
        requestDTO.setMoedaOrigem("USD");
        requestDTO.setMoedaDestino("BRL");

        ExchangeRateResponseDTO responseDTO = new ExchangeRateResponseDTO();
        responseDTO.setConversionRate(0.5F);

        when(exchangeRestClient.getExchangeRate(anyString(), anyString(), anyString()))
                .thenReturn(responseDTO);

        MoedaResponseDTO result = exchangeService.converterMoeda(requestDTO);

        assertEquals(0.5F, result.getTaxaConversao());

        verify(exchangeRestClient, times(1)).getExchangeRate(anyString(), anyString(), anyString());
        verify(moedaRepository, times(1)).persist(any(MoedaEntity.class));
    }
}