package br.com.challenge.service;

import br.com.challenge.model.ExchangeRateRequestDTO;
import br.com.challenge.model.ExchangeRateResponseDTO;
import br.com.challenge.model.MoedaEntity;
import br.com.challenge.repository.MoedaRepository;
import br.com.challenge.rest.ExchangeRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@ApplicationScoped
public class ExchangeService {

    @ConfigProperty(name = "exchangerate.api.key")
    String apiKey;

    @Inject
    @RestClient
    ExchangeRestClient exchangeRestClient;

    @Inject
    MoedaRepository moedaRepository;

    @Transactional
    public ExchangeRateResponseDTO converterMoeda(ExchangeRateRequestDTO exchangeRateRequestDTO) {
        var resp = exchangeRestClient.getExchangeRate(apiKey,
                exchangeRateRequestDTO.getMoedaOrigem(), exchangeRateRequestDTO.getMoedaDestino());

        var moedaEntity = MoedaEntity.builder()
                .moedaDestino(exchangeRateRequestDTO.getMoedaDestino())
                .moedaOrigem(exchangeRateRequestDTO.getMoedaOrigem())
                .dtConsulta(LocalDateTime.now()).build();

        moedaRepository.persist(moedaEntity);

        return resp;
    }


    public List<MoedaEntity> consultarHistorico() {
        return moedaRepository.findAll().list();
    }
}
