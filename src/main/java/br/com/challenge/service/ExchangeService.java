package br.com.challenge.service;

import br.com.challenge.model.ExchangeRateRequestDTO;
import br.com.challenge.model.ExchangeRateResponseDTO;
import br.com.challenge.rest.ExchangeRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Slf4j
@ApplicationScoped
public class ExchangeService {

    @ConfigProperty(name = "exchangerate.api.key")
    String apiKey;

    @Inject
    @RestClient
    ExchangeRestClient exchangeRestClient;

    public ExchangeRateResponseDTO converterMoeda(ExchangeRateRequestDTO exchangeRateRequestDTO) {
            return exchangeRestClient.getExchangeRate(apiKey,
                    exchangeRateRequestDTO.getMoedaOrigem(), exchangeRateRequestDTO.getMoedaDestino());
    }



}
