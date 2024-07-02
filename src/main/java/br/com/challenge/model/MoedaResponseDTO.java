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
public class MoedaResponseDTO {
    @Schema(title = "Moeda de Origem", example = "USD")
    private String moedaOrigem;

    @Schema(title = "Moeda de Destino", example = "BRL")
    private String moedaDestino;

    @Schema(title = "Taxa de Conversão Entre as Moedas", example = "5.84")
    private double taxaConversao;
}
