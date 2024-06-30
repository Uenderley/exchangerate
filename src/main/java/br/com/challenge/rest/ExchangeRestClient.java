package br.com.challenge.rest;

import br.com.challenge.model.ExchangeRateResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/v6")
@RegisterRestClient(configKey = "exchangerate-api")
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
