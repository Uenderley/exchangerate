package br.com.challenge.resource;

import br.com.challenge.model.ExchangeRateRequestDTO;
import br.com.challenge.model.MoedaResponseDTO;
import br.com.challenge.service.MoedaService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@QuarkusTest
class MoedaResourceTest {
    @InjectMock
    MoedaService exchangeService;

    @Test
    void testSucesso() {
        var resp = MoedaResponseDTO.builder().taxaConversao(0.5).build();

        when(exchangeService.converterMoeda(any(ExchangeRateRequestDTO.class)))
                .thenReturn(resp);

        ExchangeRateRequestDTO requestDTO = ExchangeRateRequestDTO.builder().moedaOrigem("USD").moedaDestino("BRL").build();
        given()
          .body(requestDTO)
          .contentType(ContentType.JSON)
          .when().post("/exchange/converter/moeda")
          .then()
             .statusCode(200)
             .body("taxaConversao", equalTo(0.5F));
    }

    @Test
    void testConverterMoedaRuntimeException() {
        ExchangeRateRequestDTO requestDTO = new ExchangeRateRequestDTO();
        requestDTO.setMoedaOrigem("USD");
        requestDTO.setMoedaDestino("BRL");

        when(exchangeService.converterMoeda(any(ExchangeRateRequestDTO.class)))
                .thenThrow(new RuntimeException("Erro ao converter moedas"));

        given()
                .contentType(ContentType.JSON)
                .body(requestDTO)
                .when()
                .post("/exchange/converter/moeda")
                .then()
                .statusCode(500);
    }
}