package br.com.challenge.exception;

import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

public class ExchangeExceptionProvider implements ResponseExceptionMapper<RuntimeException> {

    @Override
    public RuntimeException toThrowable(Response response) {
        if (response.getStatus() == 500) {
            throw new RuntimeException("The remote service responded with HTTP 500");
        }
        if (response.getStatus() == 404) {
            throw new RuntimeException("Moeda Não Existe ou Caminho Não Encontrado");
        }
        return null;
    }
}

