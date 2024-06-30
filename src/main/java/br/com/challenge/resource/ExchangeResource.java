package br.com.challenge.resource;

import br.com.challenge.model.ExchangeRateRequestDTO;
import br.com.challenge.service.ExchangeService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/exchange")
@Schema(name = "CaptchaRequestDTO")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class ExchangeResource {

    @Inject
    ExchangeService exchangeService;

    @POST
    @Path("/converter/moeda")
    @Operation(summary = "Converte um par de moedas",
            description = "Endpoint utilizado para converter um par de moedas.")
    @APIResponse(responseCode = "200", description = "Sucesso",
            content = @Content(schema = @Schema(implementation = ExchangeRateRequestDTO.class)))
    public Response converterMoeda( @RequestBody(required = true,
            content = @Content(schema = @Schema(implementation = ExchangeRateRequestDTO.class)))
                                        @Valid ExchangeRateRequestDTO parMoedasRequest) {
        var response = exchangeService.converterMoeda(parMoedasRequest);
        return Response.ok().entity(response).build();
    }
}

