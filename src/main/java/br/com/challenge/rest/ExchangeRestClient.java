package br.com.challenge.rest;

import br.com.challenge.exception.ExchangeExceptionProvider;
import br.com.challenge.model.ExchangeRateResponseDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/v6")
@RegisterRestClient(configKey = "exchangerate-api")
@RegisterProvider(ExchangeExceptionProvider.class)
public interface ExchangeRestClient {
    @GET
    @Path("/{apiKey}/pair/{fromCurrency}/{toCurrency}")
    @Produces(MediaType.APPLICATION_JSON)
    ExchangeRateResponseDTO getExchangeRate(
            @PathParam("apiKey") String apiKey,
            @PathParam("fromCurrency") String fromCurrency,
            @PathParam("toCurrency") String toCurrency
    );
}
