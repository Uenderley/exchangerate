package br.com.challenge;

import br.com.challenge.model.ExchangeRateRequestDTO;
import br.com.challenge.model.ExchangeRateResponseDTO;
import br.com.challenge.service.ExchangeService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@QuarkusTest
class ExchangeResourceTest {
    @InjectMock
    ExchangeService exchangeService;

    @Test
    void testHelloEndpoint() {
        var resp = ExchangeRateResponseDTO.builder().conversionRate(0.5).build();

        when(exchangeService.converterMoeda(any(ExchangeRateRequestDTO.class)))
                .thenReturn(resp);

        ExchangeRateRequestDTO requestDTO = ExchangeRateRequestDTO.builder().moedaOrigem("USD").moedaDestino("BRL").build();
        given()
          .body(requestDTO)
          .contentType(ContentType.JSON)
          .when().post("/exchange/converter/moeda")
          .then()
             .statusCode(200)
             .body("conversion_rate", equalTo(0.5F));
    }

}