package br.com.challenge.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateRequestDTO {
    @Schema(title = "Moeda de Origem", example = "USD", required = true)
    @NotBlank(message = "A moeda de origem não pode estar vazia!")
    private String moedaOrigem;

    @Schema(title = "Moeda de Destino", example = "BRL", required = true)
    @NotBlank(message = "A moeda de destino não pode estar vazia!")
    private String moedaDestino;
}
